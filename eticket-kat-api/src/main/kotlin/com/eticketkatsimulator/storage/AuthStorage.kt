package com.eticketkatsimulator.storage

import com.eticketkatsimulator.model.auth.AuthResponse


class AuthStorage {

    companion object {
        private val tokens = arrayListOf<AuthResponse>();

        fun addToken(token: AuthResponse) {
            tokens.add(token)
        }

        fun checkToken(token: AuthResponse) {
            tokens.contains(token)
        }

        fun remove(token: AuthResponse) {
            tokens.contains(token)
        }

    }
}