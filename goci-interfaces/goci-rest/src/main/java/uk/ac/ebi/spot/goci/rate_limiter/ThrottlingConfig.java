package uk.ac.ebi.spot.goci.rate_limiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Component
public class ThrottlingConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ThrottlingInterceptor rateLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitInterceptor);
    }

    @Bean
    public MappedInterceptor dbEditorTenantInterceptor() {
        return new MappedInterceptor(new String[]{"/**"}, rateLimitInterceptor);
    }
}
