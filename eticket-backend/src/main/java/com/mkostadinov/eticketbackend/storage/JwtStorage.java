package com.mkostadinov.eticketbackend.storage;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.mkostadinov.eticketbackend.constants.SecurityConstants.TOKEN_PREFIX;

public class JwtStorage {

    private static Set<String> createdTokens = new LinkedHashSet<>();
    private static Set<String> validSessions = new LinkedHashSet<>();

    public static void addToken(String token) {
        JwtStorage.createdTokens.add(token);
    }

    public static void delete(String token) {
        JwtStorage.createdTokens.remove(token.replaceAll(TOKEN_PREFIX, ""));
    }

    public static boolean validate(String token) {
        return JwtStorage.createdTokens.contains(token.replaceAll(TOKEN_PREFIX, ""));
    }

    public static void addSession(String sessionId) {
        JwtStorage.validSessions.add(sessionId);
    }

    public static void deleteSession(String sessionId) {
        JwtStorage.validSessions.remove(sessionId);
    }

    public static boolean validateSession(String sessionId) {
        return JwtStorage.validSessions.contains(sessionId);
    }

}
