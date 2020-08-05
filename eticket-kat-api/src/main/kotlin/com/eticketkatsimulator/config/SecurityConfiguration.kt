package com.eticketkatsimulator.config

import com.eticketkatsimulator.service.JwtSigner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import reactor.core.publisher.Mono

@Configuration
class SecurityConfiguration {
    @Bean
    fun jwtAuthenticationConverter(): ServerAuthenticationConverter {
        return ServerAuthenticationConverter { exchange ->
            Mono.justOrEmpty(exchange)
                    .flatMap { Mono.justOrEmpty(it.request.headers["Auth"]) }
                    .filter { it.isNotEmpty() }
                    .map { it[0].toString();  }
                    .map { UsernamePasswordAuthenticationToken(it, it) }
        }
    }

    @Bean
    fun jwtAuthenticationManager(jwtSigner: JwtSigner): ReactiveAuthenticationManager {
        return ReactiveAuthenticationManager { authentication ->
            Mono.just(authentication)
                    .map { jwtSigner.validateJwt(it.credentials as String) }
                    .map { jws ->
                        UsernamePasswordAuthenticationToken(
                                jws.body.subject,
                                authentication.credentials as String,
                                mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
                        )
                    }
        }
    }

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity,
                               jwtAuthenticationManager: ReactiveAuthenticationManager,
                               jwtAuthenticationConverter: ServerAuthenticationConverter): SecurityWebFilterChain {
        val authenticationWebFilter = AuthenticationWebFilter(jwtAuthenticationManager)
        authenticationWebFilter.setServerAuthenticationConverter(jwtAuthenticationConverter)

        return http.authorizeExchange()
                .pathMatchers("/vehicles/**")
                .authenticated()
                .pathMatchers("/auth/token")
                .permitAll()
                .and()
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .httpBasic()
                .disable()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .logout()
                .disable()
                .build()
    }
}