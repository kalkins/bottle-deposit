package com.sindrestephansen.bottle_deposit.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sindrestephansen.bottle_deposit.model.deposit.DepositType
import com.sindrestephansen.bottle_deposit.viewModel.DepositViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Duration.Companion.seconds

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
                onDeposit = {
                    scope.launch {
                        viewModel.deposit(it)
                    }

                    when (it) {
                        DepositType.Bottle -> 2.seconds
                        DepositType.Can -> 1.seconds
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
                onPrintReceipt = {
                    scope.launch {
                        viewModel.getReceipt()
                    }
                },
            )
        }
    }
}