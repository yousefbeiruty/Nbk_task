package com.example.data.network.repository.newsheadline

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.network.paging.GenericPagingSource
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.repository.topheadline.TopHeadLineRemoteDataRepository
import com.example.domain.repository.topheadline.TopHeadLineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadLineRepositoryImpl @Inject constructor(
    private val remoteDataSource: TopHeadLineRemoteDataRepository
) : TopHeadLineRepository {
    override suspend fun getTopHeadLine(): Flow<PagingData<ArticleHeadLine>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                GenericPagingSource(remoteDataSource,
                    {error->

                    }){
                        page ->

                    val response = remoteDataSource.getTopHeadLine(page)
                    response ?: emptyList()
                }
            }
        ).flow
    }

    override suspend fun getTopHeadLineFilter(
        country: String?,
        category: String?
    ): Flow<PagingData<ArticleHeadLine>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                GenericPagingSource(remoteDataSource,{error->

                }){
                        page ->

                    val response = remoteDataSource.getTopHeadLine(page,country,category)
                    response ?: emptyList()
                }
            }
        ).flow
    }
}