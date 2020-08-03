package com.mkostadinov.eticketbackend.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserUpdateProfileDTO {

    @JsonProperty("username")
    @Expose
    private String username;

    @JsonProperty("firstName")
    @Expose
    private String firstName;

    @JsonProperty("lastName")
    @Expose
    private String lastName;

    @JsonProperty("country")
    @Expose
    private String country;

    @JsonProperty("city")
    @Expose
    private String city;

    @JsonProperty("address")
    @Expose
    private String address;

    @JsonProperty("postCode")
    @Expose
    private String postCode;

    @JsonProperty("description")
    @Expose
    private String description;

    @JsonProperty("phoneNumber")
    @Expose
    private String phoneNumber;

    public UserUpdateProfileDTO() {
    }


    @NotBlank(message = "Username is required")
    public String getUsername() {
        return username;
    }

    public UserUpdateProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }


    @NotBlank(message = "First name is required")
    public String getFirstName() {
        return firstName;
    }

    public UserUpdateProfileDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotBlank(message = "Last name is required")
    public String getLastName() {
        return lastName;
    }

    public UserUpdateProfileDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotBlank(message = "Country name is required")
    public String getCountry() {
        return country;
    }

    public UserUpdateProfileDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    @NotBlank(message = "City name is required")
    public String getCity() {
        return city;
    }

    public UserUpdateProfileDTO setCity(String city) {
        this.city = city;
        return this;
    }

    @NotBlank(message = "Address is required")
    public String getAddress() {
        return address;
    }

    public UserUpdateProfileDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    @NotBlank(message = "Postal code is required")
    public String getPostCode() {
        return postCode;
    }

    public UserUpdateProfileDTO setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserUpdateProfileDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotBlank(message = "Phone number is required")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserUpdateProfileDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
