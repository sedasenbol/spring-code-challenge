package com.canovate.scc.config.rest;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig, CorsRegistry cors) {
        ExposureConfiguration config = restConfig.getExposureConfiguration();
        //config.forDomainType(SecurityProperties.User.class).disablePutForCreation();
        config.withItemExposure((metadata, httpMethods) -> httpMethods.enable(HttpMethod.PATCH));
    }
}
