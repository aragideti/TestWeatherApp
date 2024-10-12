package com.example.testweather.util

fun String.iconMapper(): String {
    return when (this) {
        "01d" -> "w00"
        "01n" -> "w01"
        "02d" -> "w10"
        "02n" -> "w11"
        "03d" -> "w02"
        "03n" -> "w02"
        "04d" -> "w02"
        "04n" -> "w02"
        "09d" -> "w09"
        "09n" -> "w09"
        "10d" -> "w09"
        "10n" -> "w09"
        "11d" -> "w07"
        "11n" -> "w07"
        "13d" -> "w14"
        "13n" -> "w14"
        "50d" -> "w03"
        "50n" -> "w03"
        else -> {
            "w00"
        }
    }
}

