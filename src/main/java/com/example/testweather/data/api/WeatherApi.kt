package com.example.testweather.data.api

import com.example.testweather.BuildConfig
import com.example.testweather.domain.entity.CurrentWeatherRS
import com.example.testweather.domain.entity.DailyForecastRS
import com.example.testweather.domain.entity.HourlyForecastRS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun currentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): Response<CurrentWeatherRS>

    @GET("forecast/daily")
    suspend fun dailyForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("cnt") days: String = "7",
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): Response<DailyForecastRS>

    @GET("forecast")
    suspend fun hourlyForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): Response<HourlyForecastRS>
}

