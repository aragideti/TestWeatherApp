package com.example.testweather.framework.network

import com.example.testweather.data.api.parser.ResponseFormatter
import com.example.testweather.data.api.parser.SimpleResponseFormatter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ParsingModule {

    @Provides
    @Singleton
    fun responseFormatter(): ResponseFormatter {
        return SimpleResponseFormatter()
    }
}
