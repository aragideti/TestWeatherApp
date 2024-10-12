package com.example.testweather.util

import java.text.SimpleDateFormat

object DateTimeFormatter {
    fun format(
        dateTime: Long,
        format: String
    ): String {
        return runCatching {
            val formatter = SimpleDateFormat(format)
            formatter.format(dateTime * 1000)
        }.getOrDefault("")
    }
}

enum class DateFormats(val format: String) {
    DAYWEEK_DAY_MONTH_TIME("EEEE\ndd MMMM HH:mm"),
    DAYWEEK_DAY_MONTH("EEEE, dd MMMM"),
    DAYWEEK_DAY_MONTH_HOURS("EEEE, dd MMMM\nHH:mm"),

}