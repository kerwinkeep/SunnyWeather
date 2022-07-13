package com.kk.sunnyweather.ui.place

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kk.sunnyweather.databinding.PlaceItemBinding
import com.kk.sunnyweather.logic.model.Place
import com.kk.sunnyweather.ui.weather.WeatherActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaceAdapter(private val fragment: PlaceFragment, private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    inner class ViewHolder(private var placeItemBinding: PlaceItemBinding) :
        RecyclerView.ViewHolder(placeItemBinding.root) {
        fun bind(place: Place) {
            placeItemBinding.placeName.text = place.name
            placeItemBinding.placeAddress.text = place.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val placeItemBinding =
            PlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(placeItemBinding)
        holder.itemView.setOnClickListener {
            val position = holder.bindingAdapterPosition
            val place = placeList[position]
            val activity = fragment.activity
            if (activity is WeatherActivity) {
                Log.d("tiao", "判断是WeatherActivity ")
                activity.binding.drawerLayout.closeDrawers()
                activity.viewModel.locationLng = place.location.lng
                activity.viewModel.locationLat = place.location.lat
                activity.viewModel.placeName = place.name
                activity.refreshWeather()
            } else {
                Log.d("tiao", "执行了else ")
                val intent = Intent(parent.context, WeatherActivity::class.java).apply {
                    putExtra("location_lng", place.location.lng)
                    putExtra("location_lat", place.location.lat)
                    putExtra("place_name", place.name)
                }
                fragment.startActivity(intent)
                fragment.activity?.finish()
            }
            val job = fragment.viewModel.savePlace(place)
//            MainScope().launch { job.join() }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.bind(place)
    }

    override fun getItemCount() = placeList.size
}