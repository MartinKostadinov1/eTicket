package com.mkostadinov.eticketbackend.config;

import com.mkostadinov.eticketbackend.constants.GlobalConstants;
import com.mkostadinov.eticketbackend.web.interceptors.ApiInterceptor;
import com.mkostadinov.eticketbackend.web.interceptors.ApplicationAccessInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class ApplicationWebConfig implements WebMvcConfigurer {


    @Value("${application.access-token}")
    private String applicationAccessToken;


    @Value("${application.api-token}")
    private String apiToken;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(GlobalConstants.FRONTEND_URL).allowedMethods("*").allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApplicationAccessInterceptor(this.applicationAccessToken)).addPathPatterns("/api/**").excludePathPatterns("/api/external/**");
        registry.addInterceptor(new ApiInterceptor(this.apiToken)).addPathPatterns("/api/external/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/notFound").setViewName("errors/error");
    }


    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
                    "/notFound"));
        };
    }
}
