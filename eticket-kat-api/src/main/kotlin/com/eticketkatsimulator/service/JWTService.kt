package com.eticketkatsimulator.service

interface JWTService {

    fun generate(id: String): String
}