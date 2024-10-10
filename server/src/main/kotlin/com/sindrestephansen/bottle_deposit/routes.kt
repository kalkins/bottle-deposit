package com.sindrestephansen.bottle_deposit

import com.sindrestephansen.bottle_deposit.storage.BottleDepositStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.bottleDepositRoutes(
    storage: BottleDepositStorage,
) {
    get("/api/session/init") {
        call.respond(HttpStatusCode.OK, storage.initSession())
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