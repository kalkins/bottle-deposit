package com.sindrestephansen.bottle_deposit.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sindrestephansen.bottle_deposit.model.deposit.DepositType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration

@Composable
fun DepositPane(
    modifier: Modifier,
    onDeposit: (DepositType) -> Duration,
) {
    val scope = rememberCoroutineScope()
    var enabled by remember { mutableStateOf(true) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        DepositType.entries.forEach { type ->
            Button(
                onClick = {
                    enabled = false

                    scope.launch {
                        val disabledDuration = onDeposit(type)
                        delay(disabledDuration)
                        enabled = true
                    }
                },
                modifier = Modifier.padding(10.dp),
                enabled = enabled,
            ) {
                Text(type.name)
            }
        }
    }
}