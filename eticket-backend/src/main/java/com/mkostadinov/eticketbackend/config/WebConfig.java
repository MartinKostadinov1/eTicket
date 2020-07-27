package com.mkostadinov.eticketbackend.config;

import com.mkostadinov.eticketbackend.constants.GlobalConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(GlobalConstants.FRONTEND_URL).allowCredentials(true);
    }
}
