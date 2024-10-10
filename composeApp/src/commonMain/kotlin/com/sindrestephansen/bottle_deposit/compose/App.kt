package com.sindrestephansen.bottle_deposit.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sindrestephansen.bottle_deposit.model.deposit.processingTime
import com.sindrestephansen.bottle_deposit.viewModel.DepositViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(
    viewModel: DepositViewModel = koinViewModel(),
) {
    MaterialTheme {
        val scope = rememberCoroutineScope()
        val sessionState by viewModel.sessionState.collectAsStateWithLifecycle()
        val receipt by viewModel.receipt.collectAsStateWithLifecycle()
        val error by viewModel.error.collectAsStateWithLifecycle()

        var canDeposit by remember { mutableStateOf(true) }

        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val paneModifier =
                Modifier
                    .weight(1f)
                    .fillMaxHeight()

            DepositPane(
                modifier = paneModifier,
                canDeposit = canDeposit,
                onDeposit = {
                    canDeposit = false

                    scope.launch {
                        viewModel.deposit(it)
                        delay(it.processingTime())
                        canDeposit = true
                    }
                },
            )

            StatusPane(
                paneModifier,
                state = sessionState,
                error = error,
            )

            ReceiptPane(
                paneModifier,
                currentReceipt = receipt,
                canPrintReceipt = sessionState != null,
                onPrintReceipt = {
                    scope.launch {
                        viewModel.getReceipt()
                    }
                },
            )
        }
    }
}