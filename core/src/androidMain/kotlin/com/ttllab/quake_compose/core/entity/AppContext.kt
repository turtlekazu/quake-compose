package com.ttllab.quake_compose.core.entity

import android.app.Application
import androidx.startup.Initializer

internal object AppContext {
    private lateinit var application: Application

    fun setUp(context: Context) {
        application = context as Application
    }

    fun get(): Context {
        if(::application.isInitialized.not()) throw Exception("Application context isn't initialized")
        return application.applicationContext
    }
}

internal class AppContextInitializer : Initializer<Context> {
    override fun create(context: Context): Context {
        AppContext.setUp(context.applicationContext)
        return AppContext.get()
    }
    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}