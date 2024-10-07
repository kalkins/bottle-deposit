package com.sindrestephansen.bottle_deposit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform