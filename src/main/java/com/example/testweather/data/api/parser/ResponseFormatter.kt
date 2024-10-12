package com.example.testweather.data.api.parser

import retrofit2.Response

interface ResponseFormatter {
    fun <T> parseResource(response: Response<T>): T
}
