package com.kk.sunnyweather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SunnyWeatherApplication : Application() {
    companion object {
        const val TOKEN = "thhVPKgj6nsNiGud"
    }
}