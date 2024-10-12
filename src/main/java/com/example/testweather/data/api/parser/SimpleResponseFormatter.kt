package com.example.testweather.data.api.parser

import retrofit2.Response

class SimpleResponseFormatter : ResponseFormatter {
    override fun <T> parseResource(response: Response<T>): T {
        return try {
            if (response.isSuccessful) {
                response.body() as T
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
