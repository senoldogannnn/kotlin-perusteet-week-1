package com.example.week1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week1.BuildConfig
import com.example.week1.data.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun fetchWeather(city: String) {
        if (city.isBlank()) return

        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                // BuildConfig is generated from build.gradle + local.properties
                val apiKey = BuildConfig.OPEN_WEATHER_API_KEY
                val response = RetrofitInstance.api.getWeather(city, apiKey)
                _uiState.value = WeatherUiState.Success(response)
            } catch (e: Exception) {
                // Improve error message
                val errorMsg = e.localizedMessage ?: "Unknown Error"
                _uiState.value = WeatherUiState.Error(errorMsg)
            }
        }
    }
}
