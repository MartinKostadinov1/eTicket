package com.mkostadinov.eticketbackend.web.interceptors;

import com.mkostadinov.eticketbackend.constants.SecurityConstants;
import com.mkostadinov.eticketbackend.exception.authorization.InvalidAccessTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiInterceptor implements HandlerInterceptor {


    private String apiAccessToken;

    private ApiInterceptor() {
    }

    public ApiInterceptor(String apiAccessToken) {
        this.apiAccessToken = apiAccessToken;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestApiToken = request.getHeader("ApiToken");
        if (requestApiToken != null && requestApiToken.equals(SecurityConstants.TOKEN_PREFIX + apiAccessToken)) {
            return true;
        }

        throw new InvalidAccessTokenException("Invalid or missing application api token!");
    }
}
