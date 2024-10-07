package com.sindrestephansen.bottle_deposit.model.deposit

/**
 * An ID that identifies a deposit session.
 */
class DepositSessionID private constructor(val value: String) {
    companion object {
        private var counter = 1

        /**
         * Create a random session ID.
         */
        fun random(): DepositSessionID {
            val id = DepositSessionID(counter.toString())
            counter += 1
            return id
        }
    }

    override fun toString(): String = value
    override fun hashCode(): Int = value.hashCode()
    override fun equals(other: Any?): Boolean = other is DepositSessionID && value == other.value
}
