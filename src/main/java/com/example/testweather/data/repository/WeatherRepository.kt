package com.example.testweather.data.repository

import com.example.testweather.data.api.WeatherApi
import com.example.testweather.data.api.parser.ResponseFormatter
import com.example.testweather.domain.entity.CurrentWeatherRS
import com.example.testweather.domain.entity.DailyForecastRS
import com.example.testweather.domain.entity.HourlyForecastRS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val responseFormatter: ResponseFormatter,
) {
    suspend fun getCurrentWeather(lat: String, lon: String): CurrentWeatherRS {
        return withContext(Dispatchers.IO) {
            responseFormatter
                .parseResource(
                    api.currentWeather(
                        lat = lat,
                        lon = lon,
                    )
                )
        }
    }

    suspend fun getDailyForecast(lat: String, lon: String): DailyForecastRS {
        return withContext(Dispatchers.IO) {
            responseFormatter
                .parseResource(
                    api.dailyForecast(
                        lat = lat,
                        lon = lon,
                    )
                )
        }
    }

    suspend fun getHourlyForecast(lat: String, lon: String): HourlyForecastRS {
        return withContext(Dispatchers.IO) {
            responseFormatter
                .parseResource(
                    api.hourlyForecast(
                        lat = lat,
                        lon = lon,
                    )
                )
        }
    }
}