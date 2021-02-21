package com.dester.androidcourse2020github.main

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import com.dester.androidcourse2020github.core.BaseViewModel
import kotlinx.coroutines.runBlocking

class MainScreenVM(
    lifecycleScope: LifecycleCoroutineScope,
    owner: LifecycleOwner,
    val navigateToCity: (String) -> Unit
) : BaseViewModel() {
    val popularCityName = getCityName()
    val popularCityAdapter = MainScreenAdapter { name -> navigateToCity(name) }

    init {
        Log.d("Course/VM", "SetItems: ${popularCityName.toString()}")
        fillAdapterList(lifecycleScope)
    }

    private fun fillAdapterList(lifecycleScope: LifecycleCoroutineScope) {
        popularCityName.forEach {
            getWeather(lifecycleScope, it)
        }
    }

    fun getWeather(lifecycleScope: LifecycleCoroutineScope, name: String) = runBlocking {
        val result = getCityWeather(lifecycleScope, name)
        popularCityAdapter.addItem(result)
    }


}