package com.example.data.network.services

import com.example.data.network.api.ApiRequests
import com.example.data.network.common.NetworkResult
import com.example.data.network.extensions.getModel
import com.example.data.network.extensions.map
import com.example.data.network.extensions.safeApiCall
import com.example.data.network.extensions.safeApiCallListNews
import com.example.data.network.newsheadlines.model.TopHeadLineNewsResponse
import javax.inject.Inject

class ApiManager @Inject constructor(val services: ApiRequests) {
    suspend inline fun <reified T> getRequest(
        url: String,
        headersMap: Map<String, String>? = mapOf(),
        queryParamsMap: Map<String, Any?>? = mapOf()
    ): NetworkResult<T> =
        safeApiCall {
            services.getRequest(
                url = url,
                headersMap = headersMap,
                queryParamsMap = queryParamsMap
            )
        }.map { response ->
            response?.body()?.getModel()
        }

    suspend inline fun <reified T> getTopHeadLinesNews(
        url: String,
    ): NetworkResult<List<TopHeadLineNewsResponse.Article>> =
      safeApiCallListNews {
        services.getTopHeadLines(
            url = url
        )
    }.map { response ->
            response?.body()?.articles
    }

//https://newsapi.org/v2/top-headlines?apiKey=6b8757b8d62b441c93f1fb5ad843e857&page=1&country=us
}