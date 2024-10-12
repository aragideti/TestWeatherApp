package com.example.testweather.domain.repository

import com.example.testweather.domain.entity.CurrentWeatherRS
import com.example.testweather.domain.entity.DailyForecastRS
import com.example.testweather.domain.entity.HourlyForecastRS

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: String, lon: String): CurrentWeatherRS
    suspend fun getDailyForecast(lat: String, lon: String): DailyForecastRS
    suspend fun getHourlyForecast(lat: String, lon: String): HourlyForecastRS
}