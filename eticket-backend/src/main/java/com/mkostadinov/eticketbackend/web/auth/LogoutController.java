package com.mkostadinov.eticketbackend.web;

import com.mkostadinov.eticketbackend.config.AuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LogoutController implements LogoutSuccessHandler {
    private final AuthConfig config;

    @Autowired
    public LogoutController(AuthConfig config) {
        this.config = config;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res,
                                Authentication authentication) throws IOException {
        if (req.getSession() != null) {
            req.getSession().invalidate();
        }
        String returnTo = "http://localhost:8080/";
        String logoutUrl = "https://dev-example.auth0.com/v2/logout?client_id=" +
                config.getClientId() + "&returnTo=" +returnTo;
        res.sendRedirect(logoutUrl);
    }
}