package com.sindrestephansen.bottle_deposit.storage

import com.sindrestephansen.bottle_deposit.model.deposit.DepositRequest
import com.sindrestephansen.bottle_deposit.model.deposit.DepositResult
import com.sindrestephansen.bottle_deposit.model.deposit.DepositSessionID
import com.sindrestephansen.bottle_deposit.model.deposit.DepositType
import org.slf4j.LoggerFactory

class MemoryStorage : BottleDepositStorage {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val sessions: MutableMap<DepositSessionID, MutableList<DepositType>> = mutableMapOf()

    override fun initSession(): DepositSessionID {
        while (true) {
            val id = DepositSessionID.random()
            if (!sessions.containsKey(id)) {
                sessions[id] = mutableListOf()
                return id
            }
        }
    }

    override fun deposit(request: DepositRequest) {
        sessions[request.session]?.also {
            it.add(request.type)
            logger.info("Session ${request.session}: Deposited ${request.type.name}")
        } ?: throw IllegalArgumentException("Unknown deposit session ${request.session}")
    }

    override fun endSession(session: DepositSessionID): DepositResult =
        sessions
            .remove(session)
            ?.sumOf {
                it.value
            }?.let {
                DepositResult(it)
            } ?: throw IllegalArgumentException("Unknown deposit session $session")
}