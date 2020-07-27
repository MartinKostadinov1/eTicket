package com.mkostadinov.eticketbackend.web.auth;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mkostadinov.eticketbackend.config.AuthConfig;
import com.mkostadinov.eticketbackend.storage.JwtStorage;
import com.mkostadinov.eticketbackend.utils.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.mkostadinov.eticketbackend.constants.GlobalConstants.*;

@RestController
public class AuthController {

    private final AuthConfig config;
    private final AuthenticationController authenticationController;
    private final JWTAuthentication jwtAuthentication;

    @Autowired
    public AuthController(AuthConfig config, AuthenticationController authenticationController) {
        this.config = config;
        this.authenticationController = authenticationController;
        this.jwtAuthentication = new JWTAuthentication();
    }

    @GetMapping(value = "/login")
    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizeUrl = authenticationController.buildAuthorizeUrl(request, response, LOGIN_SUCCESS_CALLBACK)
                .withScope("openid email")
                .build();
        response.sendRedirect(authorizeUrl);
    }

    @GetMapping(value = "/register")
    protected void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizeUrl = authenticationController.buildAuthorizeUrl(request, response, LOGIN_SUCCESS_CALLBACK)
                .withScope("openid email")
                .build();
        authorizeUrl = authorizeUrl.replaceAll("login", "signup");
        response.sendRedirect(authorizeUrl);
    }


    @GetMapping(value = "/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IdentityVerificationException, IOException {
        Tokens tokens = authenticationController.handle(request, response);

        DecodedJWT jwt = JWT.decode(tokens.getIdToken());
        TestingAuthenticationToken authToken2 = new TestingAuthenticationToken(jwt.getSubject(),
                jwt.getToken());
        authToken2.setAuthenticated(true);

        this.jwtAuthentication.generateToken(request, response, request.getSession().getId());
        JwtStorage.addSession(request.getSession().getId());
        response.sendRedirect(FRONTEND_URL);
    }

    @GetMapping("/token/valid")
    public ResponseEntity isTokenValid() {
        return ResponseEntity.ok().build();
    }
}