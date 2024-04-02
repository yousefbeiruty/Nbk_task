package com.example.nbktask.screens.splash

import com.example.data.cashe.manager.CachingManager
import com.example.data.cashe.manager.ProviderEnum
import com.example.nbktask.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val cachingManager: CachingManager
) : BaseViewModel() {

    private var _navigateViewState: MutableSharedFlow<SplashNavigationViewState> =
        MutableSharedFlow(replay = 1)
    val navigateViewState: SharedFlow<SplashNavigationViewState> =
        _navigateViewState.asSharedFlow()

    fun isUserLoggedIn(
    ) {
        launchCoroutine(coroutineExceptionHandler) {
            cachingManager.getProvider(ProviderEnum.PREFERENCES).getLoggedInUserEmail()
                .collectLatest {email->
                    if (email.isNullOrEmpty()){
                        _navigateViewState.emit(SplashNavigationViewState.NavigateToLogin)
                    }else{
                        _navigateViewState.emit(SplashNavigationViewState.NavigateToMain)
                    }
                }
        }
    }
}