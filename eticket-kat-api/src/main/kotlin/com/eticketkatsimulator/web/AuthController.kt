package com.eticketkatsimulator.web

import com.eticketkatsimulator.model.auth.AuthResponse
import com.eticketkatsimulator.service.JWTService
import com.eticketkatsimulator.storage.AuthStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@RestController
class AuthController @Autowired constructor(
        private val jwtService: JWTService
) {


    @PostMapping(path = ["/api/auth"], produces = [TEXT_EVENT_STREAM_VALUE])
    fun authenticate(@RequestBody payload: Map<String, String>): Mono<AuthResponse> {
        val token = jwtService.generate(payload["id"].toString())

        val authResponse = AuthResponse(token, now())

        AuthStorage.addToken(authResponse)
        return Mono.just(authResponse)
    }
}