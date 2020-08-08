package com.mkostadinov.eticketbackend.service;

import com.mkostadinov.eticketbackend.BaseTest;
import com.mkostadinov.eticketbackend.exception.user.UserNotFoundException;
import com.mkostadinov.eticketbackend.model.dto.user.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    private final String VALID_USER_AUTH_PROVIDER_ID = "auth0|5f25514219867a00372d7c0f";

    private final String NOT_VALID_USER_AUTH_PROVIDER_ID = "blbalab";

    private Principal principal;

    @Override
    protected void beforeEach() {
        super.beforeEach();
        this.principal = () -> VALID_USER_AUTH_PROVIDER_ID;
    }

    @Test
    public void shouldFindUser() {
        UserDTO user = this.userService.findCurrentUser(principal);

        Assertions.assertEquals(user.getDriverLicenseId(), "1234567890");
    }

    @Test
    public void shouldNotFindUser() {
        principal = () -> NOT_VALID_USER_AUTH_PROVIDER_ID;

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            this.userService.findCurrentUser(principal);
        });
    }

    @Test
    public void findUserByDrivingLicenseId() {
        UserDTO userDTO = this.userService.findByDriverLicenseId("1234567890");

        Assertions.assertEquals(userDTO.getCity(), "Kozloduy");
    }
}
