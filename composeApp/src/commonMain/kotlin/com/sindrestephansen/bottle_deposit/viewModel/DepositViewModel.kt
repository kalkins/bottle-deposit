package com.sindrestephansen.bottle_deposit.viewModel

import androidx.lifecycle.ViewModel
import com.sindrestephansen.bottle_deposit.client.DepositAPIClient
import com.sindrestephansen.bottle_deposit.model.deposit.DepositRequest
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionID
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionState
import com.sindrestephansen.bottle_deposit.model.deposit.DepositType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DepositViewModel(
    private val client: DepositAPIClient
) : ViewModel() {
    private val _sessionState = MutableStateFlow<DepositSessionState?>(null)
    val sessionState = _sessionState.asStateFlow()

    private val _receipt = MutableStateFlow<DepositSessionState?>(null)
    val receipt = _receipt.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private suspend fun getSession(): DepositSessionID {
        sessionState.value.let { state ->
            return if (state == null) {
                val newState = client.initSession()
                _sessionState.compareAndSet(null, newState)

                newState.session
            } else {
                state.session
            }
        }
    }

    suspend fun deposit(type: DepositType) {
        try {
            _sessionState.update {
                client.deposit(
                    DepositRequest(getSession(), type)
                )
            }

            _error.update { null }
        } catch (e: Exception) {
            _sessionState.update { null }
            _error.update { e.message }
        }
    }

    suspend fun getReceipt() {
        sessionState.value?.let { state ->
            try {
                _receipt.update {
                    client.endSession(state.session)
                }
                _error.update { null }
            } catch (e: Exception) {
                _error.update { e.message }
            }

            _sessionState.update { null }
        }
    }
}