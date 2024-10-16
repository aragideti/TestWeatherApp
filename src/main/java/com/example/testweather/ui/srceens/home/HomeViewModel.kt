package com.example.testweather.ui.srceens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testweather.domain.repository.WeatherRepository
import com.example.testweather.domain.entity.CurrentWeatherRS
import com.example.testweather.util.performRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _currentWeather = MutableLiveData<ArrayList<CurrentWeatherRS>>()
    val currentWeather: LiveData<ArrayList<CurrentWeatherRS>> = _currentWeather

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        _currentWeather.value = ArrayList()
    }

    fun fetchWeather(lat: String, lon: String) {
        viewModelScope.launch {
            performRequest(
                request = {
                    val response = repository.getCurrentWeather(lat = lat, lon = lon)
                    val currentList = _currentWeather.value ?: ArrayList()
                    currentList.add(response)
                    _currentWeather.postValue(currentList)
                },
                error = { error ->
                    _error.postValue(error.message ?: DEFAULT_ERROR_MESSAGE)
                }
            )
        }
    }

    fun removeItem(item: CurrentWeatherRS) {
        val currentList = _currentWeather.value ?: ArrayList()
        currentList.remove(item)
        _currentWeather.postValue(currentList)
    }

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "An error occurred"
    }
}