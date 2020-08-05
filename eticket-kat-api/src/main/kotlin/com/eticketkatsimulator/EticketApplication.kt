package com.eticketkatsimulator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class EticketApplication

fun main(args: Array<String>) {
	runApplication<EticketApplication>(*args)
}
