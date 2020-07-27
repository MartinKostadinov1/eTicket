package com.mkostadinov.eticketbackend.constants;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 14_400_000; // 4 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGOUT_URL = "/api/users/logout";
    public static final String SIGN_UP_URL = "/api/users/register";
    public static final String RESET_AUTH_CODE_EMAIL_URL = "/api/users/reset/auth-code/email";
    public static final String RESET_AUTH_CODE_URL = "/api/users/reset/auth-code/{id}";
    public static final String CONFIRM_EMAIL = "/api/users/confirm/email/{registrationId}";
    public static final String UPDATE_LAST_LOGIN_URL = "/api/users/last-login/update";
    public static final String SECRET = "Secret";
    public static final String TICKETS_URL = "/api/tickets";
    public static final String PAYMENTS_URL = "/api/payments";
    public static final String REST_API_KEY = "1524f024-8339-4e86-baac-517a75470123";
    public static final String GENERATE_APP_API_KEY_URL = "/api/app/key";
    public static final String APP_GENERATE_KEY_AUTH_CODE = "1455699c-6bd1-4341-a55a-a661a4b8479c";
}
