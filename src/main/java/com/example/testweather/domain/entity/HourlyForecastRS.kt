package com.example.testweather.domain.entity

import com.google.gson.annotations.SerializedName

data class HourlyForecastRS(
    val cod: String?,
    val message: Int?,
    val cnt: Int?,
    val list: List<WeatherItem>?
)

data class WeatherItem(
    val dt: Long,
    val main: MainHourly?,
    val weather: List<Weather>?,
    val clouds: Clouds?,
    val wind: WindHourly?,
    val visibility: Int?,
    val pop: Double?,
    val rain: RainHourly?,
    val sys: SysHourly?,
    @SerializedName("dt_txt")
    val dtTxt: String?
)

data class MainHourly(
    val temp: Double?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("temp_min")
    val tempMin: Double?,
    @SerializedName("temp_max")
    val tempMax: Double?,
    val pressure: Int?,
    @SerializedName("sea_level")
    val seaLevel: Int?,
    @SerializedName("grnd_level")
    val grndLevel: Int?,
    val humidity: Int?,
    @SerializedName("temp_kf")
    val tempKf: Double?
)

data class WindHourly(
    val speed: Double?,
    val deg: Int?,
    val gust: Double?
)

data class RainHourly(
    val h3: Double?
)

data class SysHourly(
    val pod: String?
)