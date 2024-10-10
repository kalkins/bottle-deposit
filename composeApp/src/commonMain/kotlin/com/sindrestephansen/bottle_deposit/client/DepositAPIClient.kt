package com.sindrestephansen.bottle_deposit.client

import com.sindrestephansen.bottle_deposit.model.deposit.DepositRequest
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionID
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionState

interface DepositAPIClient {
    suspend fun initSession(): DepositSessionID
    suspend fun deposit(request: DepositRequest): DepositSessionState
    suspend fun endSession(session: DepositSessionID): DepositSessionState
}