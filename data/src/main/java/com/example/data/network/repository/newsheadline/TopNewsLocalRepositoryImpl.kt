package com.example.data.network.repository.newsheadline

import com.example.data.cashe.manager.CachingManager
import com.example.data.cashe.manager.ProviderEnum
import com.example.data.cashe.utils.tryMapperQuery
import com.example.data.network.common.toArticleHeadLine
import com.example.data.network.common.toFavoriteNewsEntity
import com.example.domain.common.ResultWrapper
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.repository.topheadline.TopNewsLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TopNewsLocalRepositoryImpl(private val cachingManager: CachingManager) :TopNewsLocalRepository {
    override fun getFavouriteTopNews(): Flow<ResultWrapper<List<ArticleHeadLine>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getFavouriteTopNews()
        })
        { favouriteNewsEntity ->
            favouriteNewsEntity?.toArticleHeadLine()
        }
        emit(result)
    }

    override fun insertFavouriteTopNews(favouriteNews:ArticleHeadLine) = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM)
                .insertToFavouriteNews(favouriteNews.toFavoriteNewsEntity())
        }) {}
        emit(result)
    }
}