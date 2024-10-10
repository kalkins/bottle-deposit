package com.sindrestephansen.bottle_deposit.viewModel

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::DepositViewModel)
}