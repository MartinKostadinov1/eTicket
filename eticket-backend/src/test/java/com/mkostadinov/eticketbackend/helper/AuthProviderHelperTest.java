package com.mkostadinov.eticketbackend.helper;

import com.mkostadinov.eticketbackend.helpers.AuthProviderHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;

@ExtendWith(MockitoExtension.class)
public class AuthProviderHelperTest {

    private AuthProviderHelper authProviderHelperToTest;

    @BeforeEach
    public void setUp() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        this.authProviderHelperToTest = new AuthProviderHelper(builder.build());
    }

    @Test
    public void shouldGenerateTokenWillFailDueToNoCredentials() {

        Assertions.assertThrows(Exception.class, () -> {
            this.authProviderHelperToTest.generateToken();
        });
    }
}
