package com.example.data.network.common

import com.example.data.network.api.ApiRequests
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkFactory {

    fun create(
        retrofit: Retrofit
    ): ApiRequests {
        return retrofit.create(ApiRequests::class.java)
    }

    fun getRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getOkHttpClient(
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(NetworkConstants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NetworkConstants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)

        return okHttpClient.build()
    }
}