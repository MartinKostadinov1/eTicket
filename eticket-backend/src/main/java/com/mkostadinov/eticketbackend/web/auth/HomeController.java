package com.mkostadinov.eticketbackend.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String home(final Authentication authentication) {
        try {
            TestingAuthenticationToken token = (TestingAuthenticationToken) authentication;
            DecodedJWT jwt = JWT.decode(token.getCredentials().toString());
            String email = jwt.getClaims().get("email").asString();
            return String.format("<p>Welcome, %s!</p>", email);
        }catch (NullPointerException e) {
            return "errors/403";
        }
    }
}