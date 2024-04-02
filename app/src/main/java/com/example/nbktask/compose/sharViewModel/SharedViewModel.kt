package com.example.nbktask.compose.sharViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.domain.model.topheadline.ArticleHeadLine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor():ViewModel() {
     private var _articleDetails: ArticleHeadLine= ArticleHeadLine()
    var country by mutableStateOf<String>("")
        private set
    var category by mutableStateOf<String>("")
        private set
    var filtering by mutableStateOf<Boolean>(false)
        private set
    var flag by mutableStateOf<Boolean>(true)
        private set
    fun visibleBottom(visible: Boolean){
        flag=visible
    }

    fun setArticleDetails(articleHeadLine: ArticleHeadLine){
        _articleDetails=articleHeadLine
    }

    fun getArticleDetails():ArticleHeadLine{
       return _articleDetails
    }



    fun setValueCountry(countryFilter:String){
        country=countryFilter
    }

    fun setValueCategory(categoryFilter:String){
        category=categoryFilter
    }

    fun setValueFilter(filter:Boolean){
         filtering = filter
    }


}