package com.sindrestephansen.bottle_deposit.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionState

@Composable
fun StatusPane(
    modifier: Modifier,
    state: DepositSessionState?,
    error: String?,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        error?.let {
            Text(it, color = Color.Red)
        }

        Text("Current total:")

        if (state != null) {
            Text("NOK ${state.sum}")
        } else {
            Text("NOK --")
        }
    }
}