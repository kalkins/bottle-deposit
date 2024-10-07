package com.sindrestephansen.bottle_deposit

import com.sindrestephansen.bottle_deposit.storage.MemoryStorage
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json()
        }

        routing {
            val storage = MemoryStorage()
            bottleDepositRoutes(storage)
        }
    }.start(wait = true)
}