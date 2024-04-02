package com.example.nbktask.screens.splash

sealed class SplashNavigationViewState {
    object NavigateToMain : SplashNavigationViewState()
    object NavigateToLogin : SplashNavigationViewState()
}