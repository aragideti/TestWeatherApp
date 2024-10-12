package com.example.testweather.framework.network

import com.example.testweather.data.api.WeatherApi
import com.example.testweather.data.api.parser.ResponseFormatter
import com.example.testweather.data.repository.FetchWeatherRepository
import com.example.testweather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FetchModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherApi,
        responseFormatter: ResponseFormatter
    ): WeatherRepository {
        return FetchWeatherRepository(api, responseFormatter)
    }
}
