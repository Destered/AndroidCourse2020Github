package com.dester.androidcourse2020github.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dester.androidcourse2020github.WeatherResponse
import com.dester.androidcourse2020github.databinding.CardPopularCityBinding
import com.dester.androidcourse2020github.util.getWeatherPicURI
import java.util.*

class MainScreenAdapter(
    val navigateToCity: (String) -> Unit
) : RecyclerView.Adapter<MainScreenAdapter.VH>() {

    private val items: ArrayList<WeatherResponse> = arrayListOf()

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<WeatherResponse>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: WeatherResponse) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardPopularCityBinding.inflate(layoutInflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(position, items[position])
    }


    inner class VH(private val binding: CardPopularCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, popularCity: WeatherResponse) {

            Log.d("Course/Adapter", "Bind: ${popularCity.name}")

            Glide.with(binding.root.context)
                .load(getWeatherPicURI(popularCity.weather[0].icon))
                .into(binding.weatherPic)

            binding.cityName.text = popularCity.name
            binding.weatherDesc.text = popularCity.weather[0].description
            binding.tempDesc.text = "${popularCity.main.temp.toInt().toString()} c"

            itemView.setOnClickListener {
                navigateToCity(popularCity.name)
            }
        }
    }
}