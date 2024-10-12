package com.example.testweather.ui.cities

import com.example.testweather.domain.entity.CurrentWeatherRS

interface CustomClickListener {
    fun onItemClicked(item: CurrentWeatherRS?)
    fun onDeleteClicked(item: CurrentWeatherRS?)
}
