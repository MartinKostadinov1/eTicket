package com.mkostadinov.eticketbackend.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class UserDTO {

    @Expose
    private String id;

    @JsonProperty(value = "authProviderId")
    @Expose
    private String authProviderId;

    @JsonProperty(value = "driverLicenseId")
    @Expose
    private String driverLicenseId;

    @JsonProperty(value = "firstName")
    @Expose
    private String firstName;

    @JsonProperty(value = "lastName")
    @Expose
    private String lastName;

    @JsonProperty(value = "dateBorn")
    @Expose
    private LocalDateTime dateBorn;


    @JsonProperty(value = "phoneNumber")
    @Expose
    private String phoneNumber;

    @JsonProperty(value = "email")
    @Expose
    private String email;

    @JsonProperty(value = "description")
    @Expose
    private String description;

    @JsonProperty(value = "country")
    @Expose
    private String country;

    @JsonProperty(value = "city")
    @Expose
    private String city;

    @JsonProperty(value = "postCode")
    @Expose
    private String postCode;

    @JsonProperty(value = "address")
    @Expose
    private String address;

    @JsonProperty(value = "authProviderInformation")
    @Expose
    private AuthProviderUserDTO authProviderUser;

    @JsonProperty(value = "authorities")
    @Expose
    private Set<AuthorityDTO> authorities;

    @JsonProperty(value = "profilePictureUrl")
    @Expose
    private String profilePictureUrl;

    @JsonProperty(value = "profileBackgroundPictureUrl")
    @Expose
    private String profileBackgroundPictureUrl;

    public UserDTO() {
        this.authorities = new LinkedHashSet<>();
    }

    public String getId() {
        return id;
    }

    public UserDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getAuthProviderId() {
        return authProviderId;
    }

    public UserDTO setAuthProviderId(String authProviderId) {
        this.authProviderId = authProviderId;
        return this;
    }

    public String getDriverLicenseId() {
        return driverLicenseId;
    }

    public UserDTO setDriverLicenseId(String driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDateTime getDateBorn() {
        return dateBorn;
    }

    public UserDTO setDateBorn(LocalDateTime dateBorn) {
        this.dateBorn = dateBorn;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public UserDTO setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public AuthProviderUserDTO getAuthProviderUser() {
        return authProviderUser;
    }

    public UserDTO setAuthProviderUser(AuthProviderUserDTO authProviderUser) {
        this.authProviderUser = authProviderUser;
        return this;
    }

    public Set<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public UserDTO setAuthorities(Set<AuthorityDTO> authorities) {
        this.authorities = authorities;
        return this;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public UserDTO setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
        return this;
    }

    public String getProfileBackgroundPictureUrl() {
        return profileBackgroundPictureUrl;
    }

    public UserDTO setProfileBackgroundPictureUrl(String profileBackgroundPictureUrl) {
        this.profileBackgroundPictureUrl = profileBackgroundPictureUrl;
        return this;
    }

    public void update(UserUpdateProfileDTO userUpdateProfileDTO) {
        this.getAuthProviderUser().setUsername(userUpdateProfileDTO.getUsername()
        );
        this.setFirstName(userUpdateProfileDTO.getFirstName());
        this.setLastName(userUpdateProfileDTO.getLastName());
        this.setCountry(userUpdateProfileDTO.getCountry());
        this.setDescription(userUpdateProfileDTO.getDescription());
        this.setAddress(userUpdateProfileDTO.getAddress());
        this.setCity(userUpdateProfileDTO.getCity());
        this.setPostCode(userUpdateProfileDTO.getPostCode());
        this.setPhoneNumber(userUpdateProfileDTO.getPhoneNumber());
    }
}
