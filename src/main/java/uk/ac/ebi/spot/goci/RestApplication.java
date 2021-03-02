package uk.ac.ebi.spot.goci;

/**
 * Created by dwelter on 04/11/16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsProcessor;
import org.springframework.web.cors.DefaultCorsProcessor;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class RestApplication extends SpringBootServletInitializer {
    @Bean
    public CorsConfiguration getConfiguration(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setMaxAge(3599L);
        config.addAllowedMethod("GET");
        return config;
    }

    @Bean
    public CorsConfiguration postConfiguration(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setMaxAge(3599L);
        config.addAllowedMethod("POST");
        return config;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        CorsConfigurationSource source = new CorsConfigurationSource(){
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest httpServletRequest) {
                String uri = httpServletRequest.getRequestURI();
                if(httpServletRequest.getRequestURI().contains("parentMapping")){
                    return postConfiguration();
                }else{
                    return getConfiguration();
                }
            }
        };
        CorsFilter corsFilter = new CorsFilter(source);
        CorsProcessor corsProcessor = new DefaultCorsProcessor();
        corsFilter.setCorsProcessor(corsProcessor);
        FilterRegistrationBean bean = new FilterRegistrationBean(corsFilter);
        bean.setOrder(99);
        return bean;
    }
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    @PostConstruct
    public void changeRepositoryDetectionStrategy() {
        repositoryRestConfiguration.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
    }
}
