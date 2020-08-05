package com.mkostadinov.eticketbackend.service;

import com.mkostadinov.eticketbackend.model.dto.user.AuthProviderUserDTO;
import com.mkostadinov.eticketbackend.model.dto.user.UserDTO;
import com.mkostadinov.eticketbackend.model.dto.user.UserRegisterDTO;
import com.mkostadinov.eticketbackend.model.dto.user.UserUpdateProfileDTO;
import com.mkostadinov.eticketbackend.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface UserService {

    UserDTO checkIfUserExists(String authProviderId);
    UserDTO findCurrentUser(Principal principal);
    UserDTO createUserIfNotExist(UserRegisterDTO user, Principal principal);
    AuthProviderUserDTO getUserFromProvider(String authProviderUserId);
    UserDTO updateUserProfile(UserUpdateProfileDTO user, Principal principal);
    UserDTO updateUserProfilePicture(MultipartFile imageFile, String type, Principal principal);
    UserDTO updateUserProfileBackgroundPicture(MultipartFile imageFile, String type, Principal principal);
    UserDTO findByDriverLicenseId(String driverLicenseId);
    void saveUser(UserDTO userDTO);
}
