package uk.ac.ebi.spot.goci.rate_limiter;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ThrottlingInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(ThrottlingInterceptor.class);

    private final ThrottlingService rateLimiterService;

    public ThrottlingInterceptor(ThrottlingService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String clientIp = rateLimiterService.getClientIp(request);
        if (clientIp == null || clientIp.isEmpty()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing Header:api - key");
            return false;
        }

        Bucket bucket = rateLimiterService.resolveBucket(clientIp);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            response.addHeader("X - Rate - Limit - Remaining", String.valueOf(probe.getRemainingTokens()));
            return true;
        } else {
            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.addHeader("Rate - Limit - Retry - After - Seconds", String.valueOf(waitForRefill));
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "You have exhausted your API Request Quota");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
        log.debug("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) {
        log.debug("Request and Response is completed");
    }
}
