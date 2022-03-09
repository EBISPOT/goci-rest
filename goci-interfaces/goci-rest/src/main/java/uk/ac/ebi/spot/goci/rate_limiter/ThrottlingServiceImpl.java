package uk.ac.ebi.spot.goci.rate_limiter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ThrottlingServiceImpl implements ThrottlingService {

    private final Logger log = LoggerFactory.getLogger(ThrottlingServiceImpl.class);
    Map<String, Bucket> bucketCache = new ConcurrentHashMap<>();

    @Override
    public Bucket resolveBucket(String clientIp) {
        return bucketCache.computeIfAbsent(clientIp, this::newBucket);
    }

    private Bucket newBucket(String s) {
        return Bucket4j.builder()
                .addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)))).build();
    }

    @Override
    public String getClientIp(HttpServletRequest request) {
        final String LOCALHOST_IPV4 = "127.0.0.1";
        final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
        final String UNKNOWN = "unknown";

        String clientIpAddress = request.getHeader("X-Forwarded-For");
        if (!StringUtils.hasLength(clientIpAddress) || UNKNOWN.equalsIgnoreCase(clientIpAddress)) {
            clientIpAddress = request.getHeader("Proxy-Client-IP");
        }

        if (!StringUtils.hasLength(clientIpAddress) || UNKNOWN.equalsIgnoreCase(clientIpAddress)) {
            clientIpAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (!StringUtils.hasLength(clientIpAddress) || UNKNOWN.equalsIgnoreCase(clientIpAddress)) {
            clientIpAddress = request.getRemoteAddr();
            if (LOCALHOST_IPV4.equals(clientIpAddress) || LOCALHOST_IPV6.equals(clientIpAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    clientIpAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

        log.info(clientIpAddress);

        if (StringUtils.hasLength(clientIpAddress)
                && clientIpAddress.length() > 15
                && clientIpAddress.indexOf(",") > 0) {
            clientIpAddress = clientIpAddress.substring(0, clientIpAddress.indexOf(","));
        }

        return clientIpAddress;
    }
}
