package com.eticketkatsimulator.web

import com.eticketkatsimulator.model.entity.Vehicle
import com.eticketkatsimulator.repository.VehicleRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/vehicles")
class VehicleController(val vehicleRepository: VehicleRepository) {

    @GetMapping("/all")
    fun all(): Flux<Vehicle> {
        return vehicleRepository.findAll()
    }

    @GetMapping("/{driverLicenseId}")
    fun forUser(@PathVariable("driverLicenseId") driverLicenseId: String): Flux<Vehicle> {
        return this.vehicleRepository.findAllByOwnerDriverLicenseId(driverLicenseId)
    }

    @PostMapping
    fun createVehicle(@RequestBody vehicle: Vehicle): Mono<Vehicle> = vehicleRepository.save(vehicle)
}