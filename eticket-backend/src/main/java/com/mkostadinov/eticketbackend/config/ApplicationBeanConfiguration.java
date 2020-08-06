package com.mkostadinov.eticketbackend.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mkostadinov.eticketbackend.gson.AuthProviderUserGsonSerializer;
import com.mkostadinov.eticketbackend.gson.LocalDateTimeAdapter;
import com.mkostadinov.eticketbackend.model.dto.user.AuthProviderUserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(AuthProviderUserDTO.class, new AuthProviderUserGsonSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }
}
