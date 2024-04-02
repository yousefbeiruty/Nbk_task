package com.example.nbktask.compose.screens.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.usecase.topheadline.TopHeadLineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewMode   @Inject constructor(
    private val getTopHeadLineUseCase: TopHeadLineUseCase
) : ViewModel() {
    private val _articlesState: MutableStateFlow<PagingData<ArticleHeadLine>> = MutableStateFlow(value = PagingData.empty())
    val articleState: MutableStateFlow<PagingData<ArticleHeadLine>> get() = _articlesState

init {
    onEvent(HomeEvent.GetHome)
}

fun onEvent(event: HomeEvent) {
    viewModelScope.launch {
        when (event) {
            is HomeEvent.GetHome -> {
                getArticleHeadLine()
            }
        }
    }
}

private suspend fun getArticleHeadLine(){
    getTopHeadLineUseCase.invoke(Unit)
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
        .collect{
            _articlesState.value=it
        }
    }

fun getArticleHeadLine(country:String,category:String)=viewModelScope.launch(Dispatchers.IO){
        getTopHeadLineUseCase.getTopHeadLineByFilter(country,category)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect{
                _articlesState.value=it
            }
    }
}


sealed class HomeEvent {
    object GetHome : HomeEvent()
}
