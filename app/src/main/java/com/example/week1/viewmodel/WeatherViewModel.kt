package com.example.week1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.week1.data.local.AppDatabase
import com.example.week1.data.remote.RetrofitInstance
import com.example.week1.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val repository: WeatherRepository

    init {
        val database = AppDatabase.getDatabase(application)
        val weatherDao = database.weatherDao()
        val weatherApi = RetrofitInstance.api
        repository = WeatherRepository(weatherApi, weatherDao)
    }

    fun fetchWeather(city: String) {
        if (city.isBlank()) return

        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                val response = repository.getWeather(city)
                _uiState.value = WeatherUiState.Success(response)
            } catch (e: Exception) {
                val errorMsg = e.localizedMessage ?: "Unknown Error"
                _uiState.value = WeatherUiState.Error(errorMsg)
            }
        }
    }
}
