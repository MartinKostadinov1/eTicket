package com.eticketkatsimulator.repository

import com.eticketkatsimulator.model.entity.Vehicle
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface VehicleRepository : ReactiveMongoRepository<Vehicle, String> {
    fun findAllByOwnerDriverLicenseId(ownerId: String): Flux<Vehicle>;

}