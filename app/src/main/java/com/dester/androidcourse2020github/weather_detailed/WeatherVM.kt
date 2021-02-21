package com.dester.androidcourse2020github.weather_detailed

import android.graphics.drawable.Drawable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.dester.androidcourse2020github.WeatherResponse
import com.dester.androidcourse2020github.core.BaseViewModel
import com.dester.androidcourse2020github.main.getCityWeather
import com.dester.androidcourse2020github.util.DateFormatHelper
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.math.roundToInt


class WeatherVM(
    val owner: LifecycleOwner,
    val lifecycleScope: LifecycleCoroutineScope,
    val cityName: String,
    val getDrawableId: (Int) -> Drawable
) : BaseViewModel() {

    //common
    val visibility = MutableLiveData(0)

    //main
    var temp = 0
    var tempFeelsLike = 0
    val pressure = MutableLiveData(String())
    val humidity = MutableLiveData(String())
    val tempText = MutableLiveData(String())

    //sys
    val countryCode = MutableLiveData(String())
    val sunRise = MutableLiveData(String())
    val sunSet = MutableLiveData(String())

    //wind

    var windSpeed = 0.0
    var windDirection = MutableLiveData(String())
    val windText = MutableLiveData(String())

    val clouds = MutableLiveData(String())

    val gradient = MutableLiveData<Drawable>()


    init {
        cityLoadInfo(cityName)
    }

    private fun cityLoadInfo(cityName: String) = runBlocking {
        val result = getCityWeather(lifecycleScope, cityName)
        loadData(result)
    }

    private fun loadData(result: WeatherResponse) {
        temp = result.main.temp.toInt()
        tempFeelsLike = result.main.feelsLike.toInt()
        tempText.postValue(checkTemp(temp, tempFeelsLike))
        pressure.postValue("Давление: ${result.main.pressure} гПа")
        humidity.postValue("Влажность: ${result.main.humidity}%")

        countryCode.postValue(result.sys.country)
        sunRise.postValue("Восход: ${DateFormatHelper.SimpleTime.format(Date(result.sys.sunrise.toLong() * 1000))}")
        sunSet.postValue("Закат: ${DateFormatHelper.SimpleTime.format(Date(result.sys.sunset.toLong() * 1000))}")

        visibility.postValue(result.visibility)



        windSpeed = result.wind.speed
        windDirection.postValue("Направление: ${getWindDirection(result.wind.deg)}")
        windText.postValue("Ветер: $windSpeed м/сек")

        clouds.postValue("Облачность: ${result.clouds.all}%")
    }

    private fun checkTemp(temp: Int, tempFeelsLike: Int): String {
        gradient.postValue(getGradient(temp))
        return "Температура: ${temp}°   Ощущается: ${tempFeelsLike}°"
    }

    private fun getGradient(temp: Int): Drawable {
        return if (temp >= 0) getDrawableId(1)
        else getDrawableId(-1)
    }

    private fun getWindDirection(deg: Int): String {
        val caridnals = arrayOf("С", "С-З", "В", "Ю-В", "Ю", "Ю-З", "З", "С-З", "С")
        return caridnals[(deg.toDouble() % 360 / 45).roundToInt()]
    }
}