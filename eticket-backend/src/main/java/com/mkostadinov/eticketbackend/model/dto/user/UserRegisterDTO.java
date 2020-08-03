package com.mkostadinov.eticketbackend.model.dto.user;

import com.mkostadinov.eticketbackend.annotation.IsAdult;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class UserRegisterDTO {

    private String driverLicenseId;
    private String firstName;
    private String lastName;
    private LocalDateTime dateBorn;
    private String phoneNumber;
    private String description;
    private String country;
    private String city;
    private String postCode;
    private String address;

    @NotNull(message = "Driver License ID is required")
    @Length(min = 10, max = 10, message = "Driver License ID must be exactly 10 numbers")
    public String getDriverLicenseId() {
        return driverLicenseId;
    }

    public UserRegisterDTO setDriverLicenseId(String driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
        return this;
    }

    @NotNull(message = "First name is required")
    @Length(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull(message = "Last name is required")
    @Length(min = 2, max = 20, message = "Family name must be between 2 and 20 characters")
    public String getLastName() {
        return lastName;
    }

    public UserRegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Date born is required")
    @PastOrPresent(message = "Date born must be past or present")
    @IsAdult(message = "You must be 18 years old to use the platform")
    public LocalDateTime getDateBorn() {
        return dateBorn;
    }

    public UserRegisterDTO setDateBorn(LocalDateTime dateBorn) {
        this.dateBorn = dateBorn;
        return this;
    }

    @NotNull(message = "Phone number is required")
    @Length(min = 5, max = 15, message = "Phone number must be at between 5 and 15 digits")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegisterDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserRegisterDTO setDescription(String description) {
        this.description = description;
        return this;
    }


    @NotNull(message = "Country is required")
    @Length(min = 2, max = 40, message = "Country name must be at between 2 and 40 character")
    public String getCountry() {
        return country;
    }

    public UserRegisterDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    @NotNull(message = "City required")
    @Length(min = 2, max = 20, message = "City name must be at between 2 and 20 character")
    public String getCity() {
        return city;
    }

    public UserRegisterDTO setCity(String city) {
        this.city = city;
        return this;
    }

    @NotNull(message = "Post code is required")
    @Length(min = 4, max = 6, message = "Post code must be at between 4 and 6 digits")
    public String getPostCode() {
        return postCode;
    }

    public UserRegisterDTO setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    @NotNull(message = "Address is required")
    @Length(min = 3, max = 30, message = "Address must be at between 2 and 30 character")
    public String getAddress() {
        return address;
    }

    public UserRegisterDTO setAddress(String address) {
        this.address = address;
        return this;
    }
}
