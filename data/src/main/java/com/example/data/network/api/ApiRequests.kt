package com.example.data.network.api

import com.example.data.network.newsheadlines.model.TopHeadLineNewsResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

@JvmSuppressWildcards
interface ApiRequests {
    @GET
    suspend fun getRequest(
        @Url url: String,
        @HeaderMap headersMap: Map<String, String>? = mapOf(),
        @QueryMap queryParamsMap: Map<String, Any?>? = mapOf(),
    ): Response<ResponseBody>


    @GET
    suspend fun getTopHeadLines(
        @Url url: String,
    ): Response<TopHeadLineNewsResponse>


}