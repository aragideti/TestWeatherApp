package com.example.testweather.domain.entity

import com.google.gson.annotations.SerializedName

data class DailyForecastRS(
    val city: City?,
    val cod: String?,
    val message: Double?,
    val cnt: Int?,
    val list: List<WeatherData>?
)

data class City(
    val id: Int?,
    val name: String?,
    val coord: Coord?,
    val country: String?,
    val population: Int?,
    val timezone: Int?
)

data class WeatherData(
    val dt: Long,
    val sunrise: Long?,
    val sunset: Long?,
    val temp: Temp?,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike?,
    val pressure: Int?,
    val humidity: Int?,
    val weather: List<Weather>?,
    val speed: Double?,
    val deg: Int?,
    val gust: Double?,
    val clouds: Int?,
    val pop: Double?,
    val rain: Double?
)

data class Temp(
    val day: Double?,
    val min: Double?,
    val max: Double?,
    val night: Double?,
    val eve: Double?,
    val morn: Double?
)

data class FeelsLike(
    val day: Double?,
    val night: Double?,
    val eve: Double?,
    val morn: Double?
)
