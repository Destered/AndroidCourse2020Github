package com.dester.androidcourse2020github.presentation.main

import androidx.lifecycle.*
import com.dester.androidcourse2020github.data.database.EntityToResponse
import com.dester.androidcourse2020github.data.database.WeatherEntity
import com.dester.androidcourse2020github.data.repositories.CityRepository
import com.dester.androidcourse2020github.presentation.core.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainScreenVM(
    lifecycleScope: LifecycleCoroutineScope,
    owner: LifecycleOwner,
    val navigateToCity: (String) -> Unit,
    val repository: CityRepository
) : BaseViewModel() {
    val popularCityAdapter = MainScreenAdapter { name -> navigateToCity(name) }


    var latitude = MutableLiveData(35.0)
    var longitude = 139.0

    val allCity: LiveData<List<WeatherEntity>> = repository.allWords.asLiveData()

    init {
        fillAdapterList(lifecycleScope)
        latitude.observe(owner, {
            fillAdapterList(lifecycleScope)
        })

        allCity.observe(owner, { cities ->
            val list = cities.map { entity -> EntityToResponse.convertToResponse(entity) }
            cities.let { popularCityAdapter.setItems(list) }
            popularCityAdapter.notifyDataSetChanged()
        })
    }

    private fun fillAdapterList(lifecycleScope: LifecycleCoroutineScope) {
        if (latitude.value != null) {
            getCityList(lifecycleScope, latitude.value!!, longitude)
        }


    }

    fun insert(city: WeatherEntity) = viewModelScope.launch {
        repository.insert(city)
    }

    fun getCityList(lifecycleScope: LifecycleCoroutineScope, latitude: Double, longitude: Double) =
        runBlocking {
            val result = getNearCity(lifecycleScope, latitude, longitude)
            result.forEach {
                repository.insert(EntityToResponse.convertToEntity(it))
            }
            popularCityAdapter.setItems(result)
        }


}