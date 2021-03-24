package com.dester.androidcourse2020github.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dester.androidcourse2020github.*

@Entity(tableName = "city_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "clouds")
    var clouds: Clouds?,
    @ColumnInfo(name = "cod")
    var cod: Int?,
    @ColumnInfo(name = "coord")
    var coord: Coord?,
    @ColumnInfo(name = "dt")
    var dt: Int?,
    @ColumnInfo(name = "main")
    var main: Main,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "sys")
    var sys: Sys?,
    @ColumnInfo(name = "timezone")
    var timezone: Int?,
    @ColumnInfo(name = "visibility")
    var visibility: Int?,
    @ColumnInfo(name = "weather")
    var weather: List<Weather>?,
    @ColumnInfo(name = "wind")
    var wind: Wind
)