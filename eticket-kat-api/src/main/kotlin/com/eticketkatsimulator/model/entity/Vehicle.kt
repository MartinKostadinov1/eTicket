package com.eticketkatsimulator.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.*

import org.springframework.data.mongodb.core.mapping.*


@Document(collection = "vehicles")
data class Vehicle(
        @Id val id: String?,
        val vehicleRegistrationNumber: String,
        val ownerDriverLicenseId: String
)