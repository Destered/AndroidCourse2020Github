package com.dester.androidcourse2020github.main

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.dester.androidcourse2020github.core.BaseViewModel
import kotlinx.coroutines.runBlocking

class MainScreenVM(
    lifecycleScope: LifecycleCoroutineScope,
    owner: LifecycleOwner,
    val navigateToCity: (String) -> Unit
) : BaseViewModel() {
    val popularCityAdapter = MainScreenAdapter { name -> navigateToCity(name) }

    var latitude = MutableLiveData(35.0)
    var longitude = 139.0

    init {
        fillAdapterList(lifecycleScope)
        latitude.observe(owner, {
            fillAdapterList(lifecycleScope)
        })
    }

    private fun fillAdapterList(lifecycleScope: LifecycleCoroutineScope) {
        getCityList(lifecycleScope, latitude.value ?: 35.0, longitude)
    }

    fun getCityList(lifecycleScope: LifecycleCoroutineScope, latitude: Double, longitude: Double) =
        runBlocking {
            val result = getNearCity(lifecycleScope, latitude, longitude)
            popularCityAdapter.setItems(result)
        }


}