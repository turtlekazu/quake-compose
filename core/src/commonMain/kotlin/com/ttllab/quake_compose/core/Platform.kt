package com.ttllab.quake_compose.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
