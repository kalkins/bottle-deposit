package com.sindrestephansen.bottle_deposit.model.deposit

data class DepositRequest(
    val session: DepositSessionID,
    val type: DepositType,
)