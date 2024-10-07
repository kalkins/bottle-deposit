package com.sindrestephansen.bottle_deposit

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BottleDeposit",
    ) {
        App()
    }
}