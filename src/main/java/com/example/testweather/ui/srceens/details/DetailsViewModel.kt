package com.example.testweather.ui.srceens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testweather.domain.repository.WeatherRepository
import com.example.testweather.domain.entity.DailyForecastRS
import com.example.testweather.domain.entity.HourlyForecastRS
import com.example.testweather.ui.srceens.home.HomeViewModel.Companion.DEFAULT_ERROR_MESSAGE
import com.example.testweather.util.performRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _dailyForecast = MutableLiveData<DailyForecastRS>()
    val dailyForecast: LiveData<DailyForecastRS> = _dailyForecast

    private val _hourlyForecast = MutableLiveData<HourlyForecastRS>()
    val hourlyForecast: LiveData<HourlyForecastRS> = _hourlyForecast

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchDailyForecast(lat: String, lon: String) {
        viewModelScope.launch {
            performRequest(
                request = {
                    val response = repository.getDailyForecast(lat, lon)
                    _dailyForecast.postValue(response)
                },
                error = { error ->
                    _error.postValue(error.message ?: DEFAULT_ERROR_MESSAGE)
                }
            )
        }
    }

    fun fetchHourlyForecast(lat: String, lon: String) {
        viewModelScope.launch {
            performRequest(
                request = {
                    val response = repository.getHourlyForecast(lat, lon)
                    _hourlyForecast.postValue(response)
                },
                error = { error ->
                    _error.postValue(error.message ?: DEFAULT_ERROR_MESSAGE)
                }
            )
        }
    }
}