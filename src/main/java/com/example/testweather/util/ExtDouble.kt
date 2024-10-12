package com.example.testweather.util

import kotlin.math.roundToInt

fun Double.roundValue(): String {
    return this.roundToInt().toString()
}

