package com.sindrestephansen.bottle_deposit.storage

import com.sindrestephansen.bottle_deposit.model.deposit.DepositRequest
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionID
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionState
import com.sindrestephansen.bottle_deposit.model.deposit.value
import org.slf4j.LoggerFactory

class MemoryStorage : BottleDepositStorage {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val sessions: MutableMap<DepositSessionID, DepositSessionState> = mutableMapOf()

    override fun initSession(): DepositSessionID {
        while (true) {
            val id = DepositSessionID.random()
            if (!sessions.containsKey(id)) {
                sessions[id] = DepositSessionState(id)
                return id
            }
        }
    }

    override fun deposit(request: DepositRequest): DepositSessionState =
        sessions.computeIfPresent(request.session) { _, oldValue ->
            logger.info("Session ${request.session}: Deposited ${request.type.name}")

            DepositSessionState(
                session = request.session,
                sum = oldValue.sum + request.type.value()
            )
        } ?: throw IllegalArgumentException("Unknown deposit session ${request.session}")

    override fun state(session: DepositSessionID): DepositSessionState =
        sessions[session] ?: throw IllegalArgumentException("Unknown deposit session $session")

    override fun endSession(session: DepositSessionID): DepositSessionState {
        val state = sessions.remove(session)

        if (state != null) {
            logger.info("Session ${session}: Printing receipt for NOK ${state.sum}")
            return state
        } else {
            throw IllegalArgumentException("Unknown deposit session $session")
        }
    }
}