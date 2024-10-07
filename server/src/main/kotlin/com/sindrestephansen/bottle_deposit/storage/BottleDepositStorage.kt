package com.sindrestephansen.bottle_deposit.storage

import com.sindrestephansen.bottle_deposit.model.deposit.DepositRequest
import com.sindrestephansen.bottle_deposit.model.deposit.DepositResult
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionID

/**
 * A general storage interface for deposit sessions.
 */
interface BottleDepositStorage {
    /**
     * Initialize a new deposit session.
     */
    fun initSession(): DepositSessionID

    /**
     * Add a deposit to the session given by the request.
     */
    fun deposit(request: DepositRequest)

    /**
     * End a deposit session and return the total sum of the deposited items.
     */
    fun endSession(session: DepositSessionID): DepositResult
}