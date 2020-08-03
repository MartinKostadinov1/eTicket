package com.eticketkatsimulator.service.impl

import com.eticketkatsimulator.service.JWTService
import io.fusionauth.jwt.Signer
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACSigner
import org.springframework.stereotype.Service
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Service
class JWTServiceImpl : JWTService {

    override fun generate(id: String): String {
        val accessKey = "auzNN7V0aB30poSilNi15HCiE"

        val signer: Signer = HMACSigner.newSHA256Signer(accessKey)

        val jwt = JWT()
                .addClaim("id", id)
                .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusHours(2))

        return JWT.getEncoder().encode(jwt, signer)


    }
}