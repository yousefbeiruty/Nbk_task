package com.example.domain.repository.topheadline

import androidx.paging.PagingData
import com.example.domain.model.topheadline.ArticleHeadLine
import kotlinx.coroutines.flow.Flow

interface TopHeadLineRepository {
    suspend fun getTopHeadLine(): Flow<PagingData<ArticleHeadLine>>
    suspend fun getTopHeadLineFilter(country:String?=null,category:String?=null): Flow<PagingData<ArticleHeadLine>>
}