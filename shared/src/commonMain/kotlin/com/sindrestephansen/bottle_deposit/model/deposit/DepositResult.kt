package com.sindrestephansen.bottle_deposit.model.deposit

import kotlinx.serialization.Serializable

/**
 * The result of a completed deposit session.
 */
@Serializable
data class DepositResult(
    val sum: Int,
)
