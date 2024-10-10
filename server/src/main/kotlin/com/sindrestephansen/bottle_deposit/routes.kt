package com.sindrestephansen.bottle_deposit

import com.sindrestephansen.bottle_deposit.storage.BottleDepositStorage
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.bottleDepositRoutes(
    storage: BottleDepositStorage,
) {
    staticResources("/", "frontend")

    get("/api/session/init") {
        val session = storage.initSession()

        call.respond(HttpStatusCode.OK, storage.state(session))
    }

    post("/api/session/deposit") {
        call.respond(
            HttpStatusCode.OK,
            storage.deposit(call.receive()),
        )
    }

    post("/api/session/end") {
        call.respond(
            HttpStatusCode.OK,
            storage.endSession(call.receive()),
        )
    }
}