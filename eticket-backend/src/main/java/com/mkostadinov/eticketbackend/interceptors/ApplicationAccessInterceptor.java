package com.mkostadinov.eticketbackend.interceptors;


import com.mkostadinov.eticketbackend.constants.SecurityConstants;
import com.mkostadinov.eticketbackend.exception.authorization.InvalidAccessTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApplicationAccessInterceptor implements HandlerInterceptor {


    private String applicationAccessToken;

    private ApplicationAccessInterceptor() {
    }

    public ApplicationAccessInterceptor(String applicationAccessToken) {
        this.applicationAccessToken = applicationAccessToken;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestAccessToken = request.getHeader("AccessToken");
        if (requestAccessToken != null && requestAccessToken.equals(SecurityConstants.TOKEN_PREFIX + applicationAccessToken)) {
            return true;
        }

        throw new InvalidAccessTokenException("Invalid or missing application access token!");
    }

}
