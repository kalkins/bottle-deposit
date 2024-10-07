package com.sindrestephansen.bottle_deposit.model.deposit

/**
 * The types of items that can be deposited.
 */
enum class DepositType(val value: Int) {
    Bottle(3),
    Can(2),
}