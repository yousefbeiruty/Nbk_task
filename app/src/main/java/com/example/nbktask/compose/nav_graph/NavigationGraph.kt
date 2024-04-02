package com.example.nbktask.compose.nav_graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nbktask.compose.bottomnav.BottomNavItem
import com.example.nbktask.compose.bottomnav.Screen
import com.example.nbktask.compose.screens.dashboard.DashBoardScreen
import com.example.nbktask.compose.screens.details.DetailsScreen
import com.example.nbktask.compose.screens.home.HomeScreen
import com.example.nbktask.compose.sharViewModel.SharedViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = hiltViewModel()
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            sharedViewModel.visibleBottom(true)
            HomeScreen(navController,sharedViewModel=sharedViewModel)
        }
        composable(BottomNavItem.DashBoard.screen_route) {
            sharedViewModel.visibleBottom(true)
            DashBoardScreen(navController, sharedViewModel = sharedViewModel)
        }
        composable(Screen.ArticleDetailsScreen.route) {
          sharedViewModel.visibleBottom(false)
          DetailsScreen(sharedViewModel)
        }

    }
}
