package com.mkostadinov.eticketbackend.web.external.katApi;

import com.mkostadinov.eticketbackend.constants.GlobalConstants;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehiclesApiResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.MultiValueMap;

import javax.validation.Valid;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

import static com.mkostadinov.eticketbackend.constants.GlobalConstants.ETICKET_KAT_API_URL;

@Component
public class VehiclesWebClient {

    private WebClient webClient;
    private final Logger log = LoggerFactory.getLogger(VehiclesWebClient.class);

    @Value("${external.kat-api.appId}")
    private String appId;
    @Value("${external.kat-api.apiKey}")
    private String apiKey;

    private String accessToken;

    public VehiclesWebClient() {
        this.webClient = WebClient.builder().build();
    }

    public Flux<VehiclesApiResponseDTO> getRegistrationNumbers(String userDrivingLicenseId) {
        try {
            this.authenticate();
            return webClient.get()
                    .uri(ETICKET_KAT_API_URL + "/vehicles/" + userDrivingLicenseId)
                    .header("Auth", this.accessToken)
                    .retrieve()
                    .bodyToFlux(VehiclesApiResponseDTO.class)
                    .doOnError(IOException.class, e -> log.error(e.getMessage()));
        } catch (Exception e) {
            log.error("KAT API connection refused");
            return null;
        }
    }

    public Flux<VehiclesApiResponseDTO> getAllRegistrationNumbers() {
        try {
            this.authenticate();
            return webClient.get()
                    .uri(ETICKET_KAT_API_URL + "/vehicles/all")
                    .header("Auth", this.accessToken)
                    .retrieve()
                    .bodyToFlux(VehiclesApiResponseDTO.class);
        } catch (Exception e) {
            log.error("KAT API connection refused");
            return null;
        }
    }

    private void authenticate() {
        this.accessToken = Objects.requireNonNull(webClient.post()
                .uri(ETICKET_KAT_API_URL + "/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(String.format("{\"appId\":\"%s\", \"apiKey\": \"%s\"}", this.appId, this.apiKey)), String.class)
                .exchange()
                .block())
                .bodyToMono(String.class)
                .block();
    }
}
