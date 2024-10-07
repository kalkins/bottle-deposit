package com.sindrestephansen.bottle_deposit.storage

import com.sindrestephansen.bottle_deposit.model.deposit.DepositRequest
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionID
import com.sindrestephansen.bottle_deposit.model.deposit.DepositType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class MemoryStorageTest {

    /**
     * Create a random list of deposit types where each type occurs between 2 and 10 times.
     */
    private fun randomDeposits() =
        DepositType
            .entries
            .flatMap { type ->
                List((2..10).random()) {
                    type
                }
            }.shuffled()

    @Test
    fun testInitSession() {
        val storage = MemoryStorage()
        storage.initSession()
    }

    @Test
    fun testInitSessionReturnsDifferentIDs() {
        val storage = MemoryStorage()
        val session1 = storage.initSession()
        val session2 = storage.initSession()

        assertNotEquals(session1, session2)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDepositWithInvalidSession() {
        val storage = MemoryStorage()

        storage.deposit(
            DepositRequest(
                session = DepositSessionID.random(),
                type = DepositType.Can,
            )
        )
    }

    @Test
    fun testDepositWithSingleSession() {
        val storage = MemoryStorage()
        val session = storage.initSession()

        randomDeposits()
            .forEach { type ->
                storage.deposit(
                    DepositRequest(
                        session = session,
                        type = type,
                    )
                )
            }
    }

    @Test
    fun testDepositWithMultipleSessions() {
        val storage = MemoryStorage()

        randomDeposits()
            .forEach { type ->
                storage.deposit(
                    DepositRequest(
                        session = storage.initSession(),
                        type = type,
                    )
                )
            }
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDepositWithEndedSession() {
        val storage = MemoryStorage()
        val session = storage.initSession()

        storage.deposit(
            DepositRequest(
                session = session,
                type = DepositType.Can,
            )
        )

        storage.endSession(session)

        storage.deposit(
            DepositRequest(
                session = session,
                type = DepositType.Bottle,
            )
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testEndSessionWithInvalidSession() {
        val storage = MemoryStorage()

        storage.endSession(DepositSessionID.random())
    }


    @Test
    fun testEndSessionWithSingleSession() {
        val storage = MemoryStorage()
        val session = storage.initSession()

        val deposits = randomDeposits()
        val expected = deposits.sumOf { it.value }

        deposits
            .forEach { type ->
                storage.deposit(
                    DepositRequest(
                        session = session,
                        type = type,
                    )
                )
            }

        val result = storage.endSession(session)

        assertEquals(expected, result.sum)
    }

    @Test
    fun testEndSessionWithMultipleSessions() {
        val storage = MemoryStorage()
        val session1 = storage.initSession()
        val session2 = storage.initSession()

        val deposits1 = randomDeposits()
        val deposits2 = randomDeposits()
        val expected1 = deposits1.sumOf { it.value }
        val expected2 = deposits2.sumOf { it.value }

        val deposits = deposits1.map { session1 to it } + deposits2.map { session2 to it }

        deposits
            .shuffled()
            .forEach { (session, type) ->
                storage.deposit(
                    DepositRequest(
                        session = session,
                        type = type,
                    )
                )
            }

        val result1 = storage.endSession(session1)
        val result2 = storage.endSession(session2)

        assertEquals(expected1, result1.sum)
        assertEquals(expected2, result2.sum)
    }
}