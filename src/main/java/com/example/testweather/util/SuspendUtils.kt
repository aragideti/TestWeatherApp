package com.example.testweather.util

suspend fun performRequest(
    request: suspend () -> Unit,
    error: suspend ((e: Exception) -> Unit) = {}
) {
    try {
        request()
    } catch (e: Exception) {
        e.printStackTrace()
        error(e)
    }
}
