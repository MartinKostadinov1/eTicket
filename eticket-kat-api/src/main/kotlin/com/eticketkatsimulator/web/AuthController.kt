package com.eticketkatsimulator.web

import com.eticketkatsimulator.service.JwtSigner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

data class Credentials(val appId: String, val apiKey: String)

@RestController
@RequestMapping("/auth")
class AuthController @Autowired constructor(private val jwtSigner: JwtSigner) {

    private val credentials: MutableMap<String, Credentials> = mutableMapOf(
            "1" to Credentials("1", "1")
    )

    @PostMapping("/token")
    fun login(@RequestBody cred: Credentials): Mono<ResponseEntity<String>> {
        return Mono.justOrEmpty(credentials[cred.appId])
                .filter { it.apiKey == cred.apiKey }
                .map {
                    val jwt = jwtSigner.createJwt(it.appId)

                    ResponseEntity.ok().body(String.format("%s", jwt))
                }
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("")))
    }
}