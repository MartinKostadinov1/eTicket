package com.mkostadinov.eticketbackend.web.handlers;

import com.mkostadinov.eticketbackend.config.AuthConfig;
import com.mkostadinov.eticketbackend.storage.JwtStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mkostadinov.eticketbackend.constants.GlobalConstants.API_URL;
import static com.mkostadinov.eticketbackend.constants.GlobalConstants.FRONTEND_URL;

@Component
public class LogoutHandler implements LogoutSuccessHandler {

    @Value(value = "${com.auth0.domain}")
    private String domain;

    @Value(value = "${com.auth0.clientId}")
    private String clientId;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JwtStorage.deleteSession(request.getSession().getId());
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }

        String logoutUrl = domain + "/v2/logout?client_id=" +
                clientId + "&returnTo=" + API_URL;
        response.sendRedirect(FRONTEND_URL);
    }
}
