package com.example.week1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week1.viewmodel.WeatherUiState
import com.example.week1.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    var cityInput by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sääsovellus",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = cityInput,
            onValueChange = { cityInput = it },
            label = { Text("Syötä kaupunki") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.fetchWeather(cityInput) },
            enabled = cityInput.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hae sää")
        }

        Spacer(modifier = Modifier.height(32.dp))

        when (val state = uiState) {
            is WeatherUiState.Idle -> {
                Text("Hae sää syöttämällä kaupungin nimi.")
            }
            is WeatherUiState.Loading -> {
                CircularProgressIndicator()
            }
            is WeatherUiState.Success -> {
                val weather = state.response
                Text(
                    text = weather.name,
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "${weather.main.temp} °C",
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = weather.weather.firstOrNull()?.description ?: "-",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Tuuli: ${weather.wind.speed} m/s")
                Text("Ilmankosteus: ${weather.main.humidity} %")
            }
            is WeatherUiState.Error -> {
                Text(
                    text = "Virhe: ${state.message}",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
