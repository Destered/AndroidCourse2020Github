package com.dester.androidcourse2020github.data.database

import com.dester.androidcourse2020github.WeatherResponse

class EntityToResponse {
    companion object {
        fun convertToResponse(entity: WeatherEntity): WeatherResponse {
            return WeatherResponse(
                entity.id,
                entity.clouds,
                entity.cod,
                entity.coord,
                entity.dt,
                entity.main,
                entity.name,
                entity.sys,
                entity.timezone,
                entity.visibility,
                entity.weather,
                entity.wind
            )
        }

        fun convertToEntity(response: WeatherResponse): WeatherEntity {
            return WeatherEntity(
                response.id,
                response.clouds,
                response.cod,
                response.coord,
                response.dt,
                response.main,
                response.name,
                response.sys,
                response.timezone,
                response.visibility,
                response.weather,
                response.wind
            )
        }
    }
}