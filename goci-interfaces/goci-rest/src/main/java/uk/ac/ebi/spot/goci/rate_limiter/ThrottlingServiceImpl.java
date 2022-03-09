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

}
