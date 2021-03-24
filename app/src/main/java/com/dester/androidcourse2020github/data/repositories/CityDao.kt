package com.dester.androidcourse2020github.data.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dester.androidcourse2020github.data.database.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * FROM city_table")
    fun getAllCity(): Flow<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cityData: WeatherEntity)

    @Query("DELETE FROM city_table")
    suspend fun deleteAll()
}