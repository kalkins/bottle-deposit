package com.sindrestephansen.bottle_deposit.model.deposit

import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

fun DepositType.processingTime() = when(this) {
    DepositType.Bottle -> 1.seconds
    DepositType.Can -> 500.milliseconds
}