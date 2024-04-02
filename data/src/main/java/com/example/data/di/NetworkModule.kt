package com.example.data.di

import com.example.data.network.api.ApiRequests
import com.example.data.network.common.NetworkFactory
import com.example.data.network.services.ApiManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideApiRequests(retrofit: Retrofit): ApiRequests {
        return NetworkFactory.create(retrofit)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return NetworkFactory.getOkHttpClient()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return NetworkFactory.getRetrofit(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideApiManager(services: ApiRequests): ApiManager {
        return ApiManager(services)
    }

}