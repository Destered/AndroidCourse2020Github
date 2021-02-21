package com.dester.androidcourse2020github.api

import com.dester.androidcourse2020github.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeather(
        @Query("q") cityName: String
    ): WeatherResponse
}