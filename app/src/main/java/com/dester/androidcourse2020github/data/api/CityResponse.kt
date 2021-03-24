package com.dester.androidcourse2020github.data.api

import com.dester.androidcourse2020github.WeatherResponse
import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("accurate")
    val accurate: String,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("list")
    val cityList: List<WeatherResponse>
)