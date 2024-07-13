package com.ttllab.quake_compose.core.entity

import platform.Foundation.NSBundle

actual abstract class Context(val module: NSBundle? = null) {
    val main: NSBundle = NSBundle.mainBundle
}

internal class ContextImpl(module: NSBundle? = null) : Context(module)

fun NSBundle.toContext(): Context = ContextImpl(this)