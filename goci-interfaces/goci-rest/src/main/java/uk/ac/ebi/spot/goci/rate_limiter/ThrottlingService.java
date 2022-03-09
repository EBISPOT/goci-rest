package uk.ac.ebi.spot.goci.rate_limiter;

import io.github.bucket4j.Bucket;

import javax.servlet.http.HttpServletRequest;

public interface ThrottlingService {

    Bucket resolveBucket(String clientIp);
}


