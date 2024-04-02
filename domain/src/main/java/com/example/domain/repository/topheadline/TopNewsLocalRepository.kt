package com.example.domain.repository.topheadline

import com.example.domain.common.ResultWrapper
import com.example.domain.model.topheadline.ArticleHeadLine
import kotlinx.coroutines.flow.Flow

interface TopNewsLocalRepository {
    fun getFavouriteTopNews(): Flow<ResultWrapper<List<ArticleHeadLine>?>>
    fun insertFavouriteTopNews(favouriteNews:ArticleHeadLine): Flow<ResultWrapper<Unit?>>
}