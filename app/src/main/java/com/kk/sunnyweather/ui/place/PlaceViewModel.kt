package com.kk.sunnyweather.ui.place

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kk.sunnyweather.logic.Repository
import com.kk.sunnyweather.logic.model.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = viewModelScope.launch(Dispatchers.IO) {
        Repository.savePlace(place)
    }

    fun getSavedPlace() = Repository.getSavePlace()

    suspend fun isPlaceSaved() = Repository.isPlaceSaved()
}