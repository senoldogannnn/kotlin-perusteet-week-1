package com.example.week1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.week1.data.model.WeatherResponse

@Composable
fun WeatherResultSection(weather: WeatherResponse) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
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
}
