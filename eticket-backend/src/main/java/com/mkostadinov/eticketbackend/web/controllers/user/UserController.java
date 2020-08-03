package com.mkostadinov.eticketbackend.web.controllers.user;

import com.google.gson.Gson;
import com.mkostadinov.eticketbackend.constants.GlobalConstants;
import com.mkostadinov.eticketbackend.model.dto.user.UserDTO;
import com.mkostadinov.eticketbackend.model.dto.user.UserRegisterDTO;
import com.mkostadinov.eticketbackend.model.dto.user.UserUpdateProfileDTO;
import com.mkostadinov.eticketbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(GlobalConstants.APPLICATION_ENTRY_POINT + "/users")
public class UserController {

    private final UserService userService;
    private final Gson gson;

    @Autowired
    public UserController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }

    @GetMapping("/check")
    public ResponseEntity validateUserExistence(Principal principal) {
        this.userService.checkIfUserExists(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/current")
    public ResponseEntity<String> getCurrentUser(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(this.gson.toJson(this.userService.findCurrentUser(principal)));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterDTO user,
                                               Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.gson.toJson(this.userService.createUserIfNotExist(user, principal)));
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateUserProfile(@Valid @RequestBody UserUpdateProfileDTO user, Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(this.gson.toJson(this.userService.updateUserProfile(user, principal)));
    }

    @RequestMapping(value = "/profile/upload/{type}", method = RequestMethod.PUT, headers = "content-type=multipart/form-data")
    @ResponseBody
    public ResponseEntity<String> uploadMultipart(
            @RequestParam("image") final MultipartFile imageFile, @PathVariable("type") String type, Principal principal) {

        UserDTO user;
        if (type.equals(GlobalConstants.PROFILE_PICTURE_KEY)) {
            user = this.userService.updateUserProfilePicture(imageFile, type, principal);
        } else if (type.equals(GlobalConstants.PROFILE_BACKGROUND_PICTURE_KEY)) {
            user = this.userService.updateUserProfileBackgroundPicture(imageFile, type, principal);
        } else {
            throw new IllegalArgumentException(String.format("Invalid image type=%s", type));
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.gson.toJson(user));
    }
}
