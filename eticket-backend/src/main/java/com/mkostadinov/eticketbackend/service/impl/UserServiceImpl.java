package com.mkostadinov.eticketbackend.service.impl;

import com.mkostadinov.eticketbackend.exception.user.UserAlreadyExistException;
import com.mkostadinov.eticketbackend.exception.user.UserNotFoundException;
import com.mkostadinov.eticketbackend.helpers.AuthProviderHelper;
import com.mkostadinov.eticketbackend.helpers.MaskingHelper;
import com.mkostadinov.eticketbackend.model.dto.user.*;
import com.mkostadinov.eticketbackend.model.entity.User;
import com.mkostadinov.eticketbackend.repository.AuthorityRepository;
import com.mkostadinov.eticketbackend.repository.UserRepository;
import com.mkostadinov.eticketbackend.service.CloudService;
import com.mkostadinov.eticketbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final MaskingHelper maskingHelper;
    private final AuthProviderHelper authProviderHelper;
    private final AuthorityRepository authorityRepository;
    private final CloudService cloudService;

    @Value("${auth0.management.audience}")
    private String audience;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RestTemplate restTemplate, MaskingHelper maskingHelper, AuthProviderHelper authProviderHelper, AuthorityRepository authorityRepository, CloudService cloudService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
        this.maskingHelper = maskingHelper;
        this.authProviderHelper = authProviderHelper;
        this.authorityRepository = authorityRepository;
        this.cloudService = cloudService;
    }

    @Override
    public UserDTO checkIfUserExists(String authProviderId) {
        UserDTO userDTO = this.userRepository
                .findByAuthProviderId(authProviderId)
                .map(u -> this.modelMapper.map(u, UserDTO.class)).orElse(null);

        if (userDTO != null) {
            return userDTO;
        }

        throw new UserNotFoundException(String.format("User with auth0 id=%s, doesn't exists", authProviderId));
    }

    @Override
    public UserDTO findCurrentUser(Principal principal) {

        UserDTO userDTO = this.userRepository.findByAuthProviderId(principal.getName())
                .map(u -> this.modelMapper.map(u, UserDTO.class)).orElse(null);

        if (userDTO == null) {
            throw new UserNotFoundException(String.format("User with authentication provider id=%s doesn't exist", principal.getName()));
        }

        AuthProviderUserDTO authProviderUserDTO = this.getUserFromProvider(principal.getName());


        userDTO.setAuthProviderId(principal.getName());
        userDTO.setAuthProviderUser(authProviderUserDTO);
        userDTO.setEmail(authProviderUserDTO.getEmail());

        return userDTO;
    }

    @Override
    public UserDTO createUserIfNotExist(UserRegisterDTO user, Principal principal) {
        if (this.userRepository.findByDriverLicenseId(user.getDriverLicenseId()).orElse(null) != null) {
            throw new UserAlreadyExistException(
                    String.format("Could not create user with driving license id=%s. This user already exists!",
                            this.maskingHelper
                                    .maskStringWithSymbol(
                                            user.getDriverLicenseId(), '*'
                                    )
                    )
            );
        }

        String authProviderUserId = principal.getName();
        AuthProviderUserDTO authProviderUserDTO = this.getUserFromProvider(authProviderUserId);

        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        userDTO.setAuthProviderId(authProviderUserId);
        userDTO.setAuthProviderUser(authProviderUserDTO);
        userDTO.setId(null);
        userDTO.setEmail(authProviderUserDTO.getEmail());

        if (this.userRepository.count() == 0 || this.userRepository.count() == 1) {
            AuthorityDTO authorityAdmin = this.authorityRepository
                    .findByAuthorityName("ROLE_ADMIN")
                    .map(a -> this.modelMapper.map(a, AuthorityDTO.class)).orElse(null);

            userDTO.getAuthorities().add(authorityAdmin);
        } else {
            AuthorityDTO authorityUser = this.authorityRepository
                    .findByAuthorityName("ROLE_USER")
                    .map(a -> this.modelMapper.map(a, AuthorityDTO.class)).orElse(null);

            userDTO.getAuthorities().add(authorityUser);
        }

        User newUser = this.modelMapper.map(userDTO, User.class);
        this.userRepository.saveAndFlush(newUser);

        userDTO.setId(newUser.getId());
        return userDTO;

    }

    @Override
    public AuthProviderUserDTO getUserFromProvider(String authProviderUserId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.authProviderHelper.generateToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<AuthProviderUserDTO> authProviderUser = this.restTemplate
                .exchange(this.audience + "users/" + authProviderUserId, HttpMethod.GET, entity, AuthProviderUserDTO.class);

        return authProviderUser.hasBody() ? authProviderUser.getBody() : null;
    }

    @Override
    public UserDTO updateUserProfile(UserUpdateProfileDTO user, Principal principal) {
        UserDTO dbUser = this.findCurrentUser(principal);

        dbUser.update(user);


        AuthProviderUserUpdateDTO authProviderUserUpdateDTO = this.modelMapper
                .map(dbUser.getAuthProviderUser(), AuthProviderUserUpdateDTO.class);


        if (this.updateAuthProviderUser(authProviderUserUpdateDTO, principal.getName()) != null) {
            this.userRepository.saveAndFlush(this.modelMapper.map(dbUser, User.class));
        }

        return dbUser;
    }

    @Override
    public UserDTO updateUserProfilePicture(MultipartFile imageFile, String type, Principal principal) {
        UserDTO dbUser = this.findCurrentUser(principal);

        Map<String, String> params = this.cloudService.updateFile(imageFile, dbUser.getProfilePictureUrl(), type);
        String url = params.get("url");

        dbUser.setProfilePictureUrl(url);

        this.userRepository.saveAndFlush(this.modelMapper.map(dbUser, User.class));

        return dbUser;
    }

    @Override
    public UserDTO updateUserProfileBackgroundPicture(MultipartFile imageFile, String type, Principal principal) {
        UserDTO dbUser = this.findCurrentUser(principal);

        Map<String, String> params = this.cloudService.updateFile(imageFile, dbUser.getProfileBackgroundPictureUrl(), type);
        String url = params.get("url");

        dbUser.setProfileBackgroundPictureUrl(url);

        this.userRepository.saveAndFlush(this.modelMapper.map(dbUser, User.class));

        return dbUser;
    }

    private AuthProviderUserDTO updateAuthProviderUser(AuthProviderUserUpdateDTO authProviderUserUpdateDTO, String authProviderUserId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.authProviderHelper.generateToken());

        HttpEntity entity = new HttpEntity(authProviderUserUpdateDTO, headers);

        ResponseEntity<AuthProviderUserDTO> authProviderUser = this.restTemplate
                .exchange(this.audience + "users/" + authProviderUserId, HttpMethod.PATCH, entity, AuthProviderUserDTO.class);

        return authProviderUser.hasBody() ? authProviderUser.getBody() : null;
    }
}
