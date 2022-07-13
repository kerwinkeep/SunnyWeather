package com.kk.sunnyweather.logic.dao

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.kk.sunnyweather.SunnyWeatherApplication
import com.kk.sunnyweather.logic.model.Place
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object PlaceDao {

    private val applicationContext: Context = SunnyWeatherApplication.context

    private val PLACE = stringPreferencesKey("place")

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sunny_weather")

    suspend fun savePlace(place: Place) {
        applicationContext.dataStore.edit {
            it[PLACE] = Gson().toJson(place)
        }

    }

    private val placeFlow: Flow<Place> = applicationContext.dataStore.data
        .map { preferences ->
            Log.d("tttt", "取出的原始数据：${preferences[PLACE]}")
            val place = Gson().fromJson(preferences[PLACE] ?: "", Place::class.java)
            Log.d("tttt", "读取: place: $place")
            place
        }

    fun getSavePlace() = placeFlow

    fun isPlaceSaved() = applicationContext.dataStore.data
        .map { it.contains(PLACE) }
}