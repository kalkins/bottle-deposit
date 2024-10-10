package com.sindrestephansen.bottle_deposit.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sindrestephansen.bottle_deposit.model.deposit.DepositType

@Composable
fun DepositPane(
    modifier: Modifier,
    onDeposit: (DepositType) -> Unit,
    canDeposit: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        DepositType.entries.forEach { type ->
            Button(
                onClick = {
                    onDeposit(type)
                },
                modifier = Modifier.padding(10.dp),
                enabled = canDeposit,
            ) {
                Text(type.name)
            }
        }
    }
}