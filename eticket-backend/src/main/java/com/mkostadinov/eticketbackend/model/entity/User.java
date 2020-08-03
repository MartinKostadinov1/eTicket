package com.mkostadinov.eticketbackend.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String authProviderId;
    private String email;
    private String driverLicenseId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String description;
    private String country;
    private String city;
    private String postCode;
    private String address;
    private LocalDateTime dateBorn;
    private Set<Authority> authorities = new LinkedHashSet<>();
    private String profilePictureUrl;
    private String profileBackgroundPictureUrl;

    public User() {
    }

    @Column(name = "auth_provider_id", unique = true, nullable = false)
    public String getAuthProviderId() {
        return authProviderId;
    }

    public User setAuthProviderId(String authProviderId) {
        this.authProviderId = authProviderId;
        return this;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "driver_license_id", nullable = false, unique = true)
    public String getDriverLicenseId() {
        return driverLicenseId;
    }

    public User setDriverLicenseId(String driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
        return this;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(name = "phone_number", nullable = false, unique = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    public User setCountry(String country) {
        this.country = country;
        return this;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    @Column(name = "post_code", nullable = false)
    public String getPostCode() {
        return postCode;
    }

    public User setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    @Column(name = "date_born", nullable = false)
    public LocalDateTime getDateBorn() {
        return dateBorn;
    }

    public User setDateBorn(LocalDateTime dateBorn) {
        this.dateBorn = dateBorn;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public User setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
        return this;
    }

    @Column(name = "profile_picture_url", unique = true)
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public User setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
        return this;
    }

    @Column(name = "profile_background_picture_url", unique = true)
    public String getProfileBackgroundPictureUrl() {
        return profileBackgroundPictureUrl;
    }

    public User setProfileBackgroundPictureUrl(String profileBackgroundPictureUrl) {
        this.profileBackgroundPictureUrl = profileBackgroundPictureUrl;
        return this;
    }
}
