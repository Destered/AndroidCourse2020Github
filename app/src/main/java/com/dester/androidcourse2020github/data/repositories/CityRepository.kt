package com.dester.androidcourse2020github.data.repositories

import androidx.annotation.WorkerThread
import com.dester.androidcourse2020github.data.database.WeatherEntity
import kotlinx.coroutines.flow.Flow

class CityRepository(private val cityDao: CityDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<WeatherEntity>> = cityDao.getAllCity()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(city: WeatherEntity) {
        cityDao.insert(city)
    }
}