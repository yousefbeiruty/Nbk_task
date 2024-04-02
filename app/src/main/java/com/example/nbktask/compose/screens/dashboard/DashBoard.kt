package com.example.nbktask.compose.screens.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nbktask.compose.screens.home.CardDemo
import com.example.nbktask.compose.sharViewModel.SharedViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.nbktask.R
import com.example.nbktask.utils.EmptyList


@Composable
fun DashBoardScreen(navController: NavController,
                    viewModel:DashboardViewModel = hiltViewModel(),
                    sharedViewModel:SharedViewModel = hiltViewModel()) {
    LaunchedEffect(Unit){
        viewModel.getFavouriteNews()
    }
    val articleList=viewModel.articleState.collectAsState()
    if(articleList.value.isNullOrEmpty()){
        EmptyList(stringResource(R.string.empty_favourite_list))
    }else
    FavouriteNewsList(articleList, navController, sharedViewModel)
}

@Composable
private fun FavouriteNewsList(
    articleList: State<List<ArticleHeadLine>>,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(articleList.value.size) { index ->
            CardDemo(articleList.value[index], navController, sharedViewModel)
        }
        item { Spacer(modifier = Modifier.padding(bottom = 50.dp)) }

    }
}