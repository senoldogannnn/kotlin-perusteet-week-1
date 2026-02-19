package com.example.week1.data.repository

import android.util.Log
import com.example.week1.data.local.WeatherDao
import com.example.week1.data.local.WeatherEntity
import com.example.week1.data.model.Main
import com.example.week1.data.model.Weather
import com.example.week1.data.model.WeatherResponse
import com.example.week1.data.model.Wind
import com.example.week1.data.remote.WeatherApi

class WeatherRepository(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) {

    suspend fun getWeather(city: String): WeatherResponse {
        val currentTime = System.currentTimeMillis()
        val thirtyMinutesInMillis = 30 * 60 * 1000

        val cachedWeather = weatherDao.getWeather(city)

        if (cachedWeather != null) {
            val age = currentTime - cachedWeather.timestamp
            if (age < thirtyMinutesInMillis) {
                return mapEntityToResponse(cachedWeather)
            }
        }

        try {
            val apiKey = com.example.week1.BuildConfig.OPEN_WEATHER_API_KEY
            val response = weatherApi.getWeather(city, apiKey)

            val entity = mapResponseToEntity(response, currentTime)
            weatherDao.insertWeather(entity)
            
            return response

        } catch (e: Exception) {
            if (cachedWeather != null) {
                return mapEntityToResponse(cachedWeather)
            }
            throw e
        }
    }

    private fun mapEntityToResponse(entity: WeatherEntity): WeatherResponse {
        return WeatherResponse(
            name = entity.cityName,
            main = Main(temp = entity.temp, humidity = entity.humidity),
            weather = listOf(Weather(description = entity.description, icon = "")),
            wind = Wind(speed = entity.windSpeed)
        )
    }

    private fun mapResponseToEntity(response: WeatherResponse, timestamp: Long): WeatherEntity {
        return WeatherEntity(
            cityName = response.name,
            temp = response.main.temp,
            description = response.weather.firstOrNull()?.description ?: "",
            windSpeed = response.wind.speed,
            humidity = response.main.humidity,
            timestamp = timestamp
        )
    }
}
