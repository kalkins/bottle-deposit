package com.sindrestephansen.bottle_deposit.model.deposit

import kotlinx.serialization.Serializable

@Serializable
data class DepositRequest(
    val session: DepositSessionID,
    val type: DepositType,
)