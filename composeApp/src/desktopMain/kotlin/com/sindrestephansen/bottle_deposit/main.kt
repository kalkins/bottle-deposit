package com.sindrestephansen.bottle_deposit

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sindrestephansen.bottle_deposit.client.clientModule
import com.sindrestephansen.bottle_deposit.compose.App
import com.sindrestephansen.bottle_deposit.viewModel.viewModelModule
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BottleDeposit",
    ) {
        KoinApplication(KoinApplication::koinConfiguration) {
            App()
        }
    }
}

fun KoinApplication.koinConfiguration() {
    modules(
        clientModule,
        viewModelModule,
    )
}