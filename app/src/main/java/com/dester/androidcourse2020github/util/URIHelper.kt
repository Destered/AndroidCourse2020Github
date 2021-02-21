package com.dester.androidcourse2020github.util

fun getWeatherPicURI(name: String): String {
    return "http://openweathermap.org/img/wn/$name@2x.png"
}

fun getCountryPicURI(countryCode: String): String {
    return "https://flagcdn.com/64x48/${countryCode.toLowerCase()}.png"
}