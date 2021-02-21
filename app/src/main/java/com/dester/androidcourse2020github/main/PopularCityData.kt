package com.dester.androidcourse2020github.main

import androidx.lifecycle.LifecycleCoroutineScope
import com.dester.androidcourse2020github.WeatherResponse
import com.dester.androidcourse2020github.api.ApiFactory


fun getCityName() = arrayListOf<String>(
    "Казань",
    "Москва",
    "Октябрьский",
    "Киев",
    "Хиросима",
    "Берлин",
    "Иваново",
    "Бангладеш",
    "Париж",
    "Лондон",
    "Осака",
    "Фукуока",
    "Киото",
    "Уфа",
    "Самара",
    "Туапсе",
    "Караганда",
    "Вена",
    "Брест",
    "Эфиопия"
)

suspend fun getCityWeather(lifecycleScope: LifecycleCoroutineScope, name: String): WeatherResponse {
    ApiFactory.weatherApi.getWeather(name).run {
        return this
    }
}