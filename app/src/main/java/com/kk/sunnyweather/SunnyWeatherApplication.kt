package com.kk.sunnyweather

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltAndroidApp
class SunnyWeatherApplication : Application() {

    companion object {
        const val TOKEN = "thhVPKgj6nsNiGud"
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

    }
}