package com.sindrestephansen.bottle_deposit.client

import com.sindrestephansen.bottle_deposit.model.deposit.DepositRequest
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionID
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionState
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class BaseDepositAPIClient(
    private val client: HttpClient,
) : DepositAPIClient {
    override suspend fun initSession(): DepositSessionID =
        client.get("/api/session/init").body()

    override suspend fun deposit(request: DepositRequest): DepositSessionState =
        client.post("/api/session/deposit") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun endSession(session: DepositSessionID): DepositSessionState =
        client.post("/api/session/end") {
            contentType(ContentType.Application.Json)
            setBody(session)
        }.body()
}
