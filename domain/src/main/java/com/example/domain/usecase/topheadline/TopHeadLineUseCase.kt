package com.example.domain.usecase.topheadline

import androidx.paging.PagingData
import com.example.domain.common.BaseUseCase
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.repository.topheadline.TopHeadLineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadLineUseCase @Inject constructor(
    private val repository: TopHeadLineRepository
) : BaseUseCase<Unit, Flow<PagingData<ArticleHeadLine>>> {
    override suspend fun invoke(params: Unit?): Flow<PagingData<ArticleHeadLine>> {
        return repository.getTopHeadLine()
    }

   suspend fun getTopHeadLineByFilter(country:String?=null,category:String?=null): Flow<PagingData<ArticleHeadLine>>{
        return repository.getTopHeadLineFilter(country,category)
    }
}