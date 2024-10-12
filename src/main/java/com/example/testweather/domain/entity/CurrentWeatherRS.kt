package com.example.testweather.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeatherRS(
    val coord: Coord,
    val weather: ArrayList<Weather>,
    val base: String?,
    val main: Main?,
    val visibility: Int?,
    val wind: Wind?,
    val rain: Rain?,
    val clouds: Clouds?,
    val dt: Long,
    val sys: Sys?,
    val timezone: Int?,
    val id: Int?,
    val name: String?,
    val cod: Int?
) : Parcelable

@Parcelize
data class Coord(
    val lon: String,
    val lat: String
) : Parcelable

@Parcelize
data class Weather(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
) : Parcelable

@Parcelize
data class Main(
    val temp: Double?,
    val feelsLike: Double?,
    val tempMin: Double?,
    val tempMax: Double?,
    val pressure: String?,
    val humidity: String?,
    val seaLevel: Int?,
    val grndLevel: Int?
) : Parcelable

@Parcelize
data class Wind(
    val speed: Double?,
    val deg: Int?,
    val gust: Double?
) : Parcelable

@Parcelize
data class Rain(
    @SerializedName("1h")
    val oneH: Double?
) : Parcelable

@Parcelize
data class Clouds(
    val all: Int?
) : Parcelable

@Parcelize
data class Sys(
    val type: Int?,
    val id: Int?,
    val country: String?,
    val sunrise: Int?,
    val sunset: Int?
) : Parcelable