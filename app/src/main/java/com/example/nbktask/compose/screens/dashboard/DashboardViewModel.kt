package com.example.nbktask.compose.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.common.ResultWrapper
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.usecase.topheadline.TopHeadLineLocalUseCase
import com.example.nbktask.common.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getTopHeadLineLocalUseCase: TopHeadLineLocalUseCase,
) : ViewModel() {
    private val _articlesState: MutableStateFlow<List<ArticleHeadLine>> = MutableStateFlow(emptyList())
    val articleState: MutableStateFlow<List<ArticleHeadLine>> get() = _articlesState

     suspend fun getFavouriteNews() {
        getTopHeadLineLocalUseCase.invoke(Unit)
            .collect {result->

                when (result) {
                    is ResultWrapper.Success -> {
                        result.data?.let { it.toList().let {artilList->
                            articleState.value=artilList
                        } }
                    }
                    is ResultWrapper.Error -> {

                    }

                    else -> {}
                }
            }
    }
}