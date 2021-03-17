package com.example.catapp

import android.app.Application
import android.content.Context

class MyApp : Application() {

    companion object {
        lateinit var instance: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
    }
}
