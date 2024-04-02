package com.example.data.network.newsheadlines

import com.example.data.network.common.NetworkResult
import com.example.data.network.newsheadlines.model.TopHeadLineNewsResponse
import com.example.data.network.services.ApiManager
import javax.inject.Inject

class NewsHeadLinesService @Inject constructor(private val apiManager: ApiManager) {
    companion object {
        private const val KEY = "6b8757b8d62b441c93f1fb5ad843e857"
        private const val API_KEY = "apiKey"
        const val SERVER_URL = "https://newsapi.org/v2/"
        private const val PATH_EVENT_DETAILS = "top-headlines"
    }

    suspend fun getTopHeadLinesNews(
        country: String?=null,
        page: Int,
        category: String?=null
    ): NetworkResult<List<TopHeadLineNewsResponse.Article>> {
        val resolvedCountry = country ?: "us"
        val resolvedCategory = category ?: "general"
        return apiManager.getTopHeadLinesNews<TopHeadLineNewsResponse>(
            url = "$SERVER_URL$PATH_EVENT_DETAILS?$API_KEY=$KEY&page=$page&country=$resolvedCountry&category=$resolvedCategory"
        )
    }
}