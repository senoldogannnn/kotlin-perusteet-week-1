package com.example.week1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey
    val cityName: String,
    val temp: Double,
    val description: String,
    val windSpeed: Double,
    val humidity: Int,
    val timestamp: Long
)
