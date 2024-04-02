package com.example.nbktask.compose.bottomnav

import com.example.nbktask.R
import com.example.nbktask.utils.SCREEN_ROOT_DASH_BOARD
import com.example.nbktask.utils.FAVOURITE_NEWS
import com.example.nbktask.utils.HOME
import com.example.nbktask.utils.SCREEN_ROOT_HOME
import com.example.nbktask.utils.SCREEN_ROOT_ARTICLE_DETAILS

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem(HOME, R.drawable.ic_home_black_24dp,SCREEN_ROOT_HOME)
    object DashBoard: BottomNavItem(FAVOURITE_NEWS,R.drawable.ic_dashboard_black_24dp,SCREEN_ROOT_DASH_BOARD)
}

sealed class  Screen(val route:String){
    object ArticleDetailsScreen:Screen(SCREEN_ROOT_ARTICLE_DETAILS)
}