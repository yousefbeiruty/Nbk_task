package com.example.nbktask.compose.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.usecase.topheadline.TopHeadLineLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class DetailsViewModel @Inject constructor(
    private val getTopHeadLineLocalUseCase: TopHeadLineLocalUseCase,
) : ViewModel() {

    fun insertToFavouriteNews(articleHeadLine: ArticleHeadLine) = viewModelScope.launch{
        getTopHeadLineLocalUseCase.insertFavouriteNews(articleHeadLine)
    }
}