package com.mkostadinov.eticketbackend.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

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


    @NotNull(message = "Username is required")
    @Length(min = 2, max = 20, message = "Username must be between 2 and 20 characters")
    public String getUsername() {
        return username;
    }

    public UserUpdateProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }


    @NotNull(message = "First name is required")
    @Length(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    public String getFirstName() {
        return firstName;
    }

    public UserUpdateProfileDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull(message = "Last name is required")
    @Length(min = 2, max = 30, message = "First name must be between 2 and 20 characters")
    public String getLastName() {
        return lastName;
    }

    public UserUpdateProfileDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotNull(message = "Country is required")
    @Length(min = 2, max = 40, message = "Country name must be at between 2 and 40 character")
    public String getCountry() {
        return country;
    }

    public UserUpdateProfileDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    @NotNull(message = "City required")
    @Length(min = 2, max = 20, message = "City name must be at between 2 and 20 character")
    public String getCity() {
        return city;
    }

    public UserUpdateProfileDTO setCity(String city) {
        this.city = city;
        return this;
    }

    @NotNull(message = "Address is required")
    @Length(min = 3, max = 30, message = "Address must be at between 2 and 30 character")
    public String getAddress() {
        return address;
    }

    public UserUpdateProfileDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    @NotNull(message = "Post code is required")
    @Length(min = 4, max = 6, message = "Post code must be at between 4 and 6 digits")
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

    @NotNull(message = "Phone number is required")
    @Length(min = 5, max = 15, message = "Phone number must be at between 5 and 15 digits")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserUpdateProfileDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
