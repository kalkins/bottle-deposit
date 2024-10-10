package com.sindrestephansen.bottle_deposit.model.deposit

fun DepositType.value() = when (this) {
    DepositType.Bottle -> 3
    DepositType.Can -> 2
}