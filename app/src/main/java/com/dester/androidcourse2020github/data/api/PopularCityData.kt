package com.dester.androidcourse2020github.presentation.main

import androidx.lifecycle.LifecycleCoroutineScope
import com.dester.androidcourse2020github.WeatherResponse
import com.dester.androidcourse2020github.data.api.ApiFactory

suspend fun getCityWeather(lifecycleScope: LifecycleCoroutineScope, name: String): WeatherResponse {
    ApiFactory.weatherApi.getWeather(name).run {
        return this
    }
}

suspend fun getNearCity(
    lifecycleScope: LifecycleCoroutineScope,
    latitude: Double,
    longitude: Double
): List<WeatherResponse> {
    ApiFactory.weatherApi.getNearCity(latitude, longitude).run {
        return this.cityList
    }
}