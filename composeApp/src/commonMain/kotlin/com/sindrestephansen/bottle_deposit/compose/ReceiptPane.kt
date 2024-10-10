package com.sindrestephansen.bottle_deposit.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionState

@Composable
fun ReceiptPane(
    modifier: Modifier,
    onPrintReceipt: () -> Unit,
    currentReceipt: DepositSessionState?,
    canPrintReceipt: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { onPrintReceipt() },
            enabled = canPrintReceipt,
        ) {
            Text("Print receipt")
        }

        if (currentReceipt != null) {
            Surface(
                color = Color.LightGray
            ) {
                Column(
                    Modifier.padding(32.dp, 24.dp)
                ) {
                    Text("Receipt:\n")
                    Text("NOK ${currentReceipt.sum}")
                }
            }
        }
    }
}