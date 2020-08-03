package com.eticketkatsimulator.model.auth

import java.time.LocalDateTime

data class AuthResponse(val token: String, val issued: LocalDateTime)
