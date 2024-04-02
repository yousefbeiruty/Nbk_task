package com.example.domain.usecase.topheadline

import com.example.domain.common.BaseUseCase
import com.example.domain.common.ResultWrapper
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.repository.topheadline.TopNewsLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class TopHeadLineLocalUseCase @Inject constructor(
    private val localRepository: TopNewsLocalRepository
): BaseUseCase<Unit,
        Flow<ResultWrapper<List<ArticleHeadLine>?>>> {
    override suspend fun invoke(params: Unit?): Flow<ResultWrapper<List<ArticleHeadLine>?>> {
        return localRepository.getFavouriteTopNews()
    }

    suspend fun insertFavouriteNews(articleHeadLine: ArticleHeadLine) {
      return localRepository.insertFavouriteTopNews(
                articleHeadLine
            ).collect()
    }
}