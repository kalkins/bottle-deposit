package com.sindrestephansen.bottle_deposit

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.sindrestephansen.bottle_deposit.client.clientModule
import com.sindrestephansen.bottle_deposit.compose.App
import com.sindrestephansen.bottle_deposit.viewModel.viewModelModule
import kotlinx.browser.document
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
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
