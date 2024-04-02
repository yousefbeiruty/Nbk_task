package com.example.domain.repository.topheadline

import com.example.domain.model.topheadline.ArticleHeadLine

interface TopHeadLineRemoteDataRepository {
    suspend fun getTopHeadLine(pageNumber: Int,country:String?=null,
                               category:String?=null): List<ArticleHeadLine>?
}