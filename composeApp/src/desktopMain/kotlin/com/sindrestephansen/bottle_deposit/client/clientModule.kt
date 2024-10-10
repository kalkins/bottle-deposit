package com.sindrestephansen.bottle_deposit.client

import com.sindrestephansen.bottle_deposit.SERVER_PORT
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val clientModule = module {
    single {
        HttpClient(CIO) {
            defaultRequest {
                host = "localhost"
                port = SERVER_PORT
            }

            install(ContentNegotiation) {
                json()
            }
        }
    }

    singleOf(::BaseDepositAPIClient) {
        bind<DepositAPIClient>()
    }
}