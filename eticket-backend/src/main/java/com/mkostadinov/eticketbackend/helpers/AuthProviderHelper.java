package com.mkostadinov.eticketbackend.helpers;

import com.mkostadinov.eticketbackend.model.dto.auth0.AccessTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;


@Component
public class AuthProviderHelper {


    private String managementApiToken;

    @Value("${auth0.management.audience}")
    private String audience;

    @Value("${auth0.management.client_id}")
    private String client_id;

    @Value("${auth0.management.secret}")
    private String secret;


    private final RestTemplate restTemplate;


    public String getManagementApiToken() {
        return managementApiToken;
    }

    public AuthProviderHelper setManagementApiToken(String managementApiToken) {
        this.managementApiToken = managementApiToken;
        return this;
    }

    @Autowired
    public AuthProviderHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateToken() {


        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(String.format("{\"client_id\":\"%s\",\"client_secret\":\"%s\",\"audience\":\"%s\",\"grant_type\":\"client_credentials\"}", this.client_id, this.secret, this.audience),headers);


        ResponseEntity<AccessTokenDTO> response = this.restTemplate
                .exchange("https://eticket.eu.auth0.com/oauth/token", HttpMethod.POST, entity, AccessTokenDTO.class);

        if(response.hasBody()) {
            this.managementApiToken = response.getBody().getTokenType() + " " + response.getBody().getAccessToken();
        }


        return this.managementApiToken;

    }

}
