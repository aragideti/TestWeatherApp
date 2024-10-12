package com.example.testweather.framework

import com.example.testweather.BuildConfig.BASE_URL
import com.example.testweather.data.api.WeatherApi
import com.example.testweather.framework.network.ApiModuleProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule : ApiModuleProvider<WeatherApi> {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    override fun provideApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create()
    }
}
