package com.sindrestephansen.bottle_deposit.model.deposit

import kotlinx.serialization.Serializable

/**
 * The state of a deposit session.
 */
@Serializable
data class DepositSessionState(
    val session: DepositSessionID,
    val sum: Int = 0,
)
