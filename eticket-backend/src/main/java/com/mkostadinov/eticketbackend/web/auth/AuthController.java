package com.mkostadinov.eticketbackend.web.auth;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mkostadinov.eticketbackend.config.AuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mkostadinov.eticketbackend.constants.GlobalConstants.API_URL;
import static com.mkostadinov.eticketbackend.constants.GlobalConstants.LOGIN_SUCCESS_CALLBACK;

@Controller
public class AuthController {

    private final AuthConfig config;
    private final AuthenticationController authenticationController;

    @Autowired
    public AuthController(AuthConfig config, AuthenticationController authenticationController) {
        this.config = config;
        this.authenticationController = authenticationController;
    }

    @GetMapping(value = "/login")
    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizeUrl = authenticationController.buildAuthorizeUrl(request, response, LOGIN_SUCCESS_CALLBACK)
                .withScope("openid email")
                .build();
        response.sendRedirect(authorizeUrl);
    }


    @GetMapping(value = "/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IdentityVerificationException, IOException {
        Tokens tokens = authenticationController.handle(request, response);

        DecodedJWT jwt = JWT.decode(tokens.getIdToken());
        TestingAuthenticationToken authToken2 = new TestingAuthenticationToken(jwt.getSubject(),
                jwt.getToken());
        authToken2.setAuthenticated(true);

        SecurityContextHolder.getContext().setAuthentication(authToken2);
        response.sendRedirect(API_URL);
    }
}