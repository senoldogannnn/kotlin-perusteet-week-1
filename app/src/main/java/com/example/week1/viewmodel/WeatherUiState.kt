package com.example.week1.viewmodel

import com.example.week1.model.WeatherResponse

sealed interface WeatherUiState {
    object Idle : WeatherUiState
    object Loading : WeatherUiState
    data class Success(val response: WeatherResponse) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}
