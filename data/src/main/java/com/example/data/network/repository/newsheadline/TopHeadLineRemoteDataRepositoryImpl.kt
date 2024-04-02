package com.example.data.network.repository.newsheadline

import com.example.data.network.extensions.map
import com.example.data.network.extensions.toArticleHeadLine
import com.example.data.network.newsheadlines.NewsHeadLinesService
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.repository.topheadline.TopHeadLineRemoteDataRepository
import javax.inject.Inject

class TopHeadLineRemoteDataRepositoryImpl @Inject constructor(
    private val api: NewsHeadLinesService
) : TopHeadLineRemoteDataRepository {
    override suspend fun getTopHeadLine(
        pageNumber: Int,
        country: String?,
        category: String?
    ): List<ArticleHeadLine>? {
        val data= api.getTopHeadLinesNews(page = pageNumber, country = country, category = category)
        println("API Response: $data")
        val list= ArrayList<ArticleHeadLine>()
        data.map {
            return  it?.toArticleHeadLine()
        }
        return list
    }
}