package com.example.nbktask.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 *
 * Main base [BaseViewModel]
 */
abstract class BaseViewModel : ViewModel() {

    val state: StateFlow<BaseViewState>
        get() = _state

    var _state =
        MutableStateFlow<BaseViewState>(BaseViewState.Idle)

    /**
     * The [launchCoroutine]
     *
     * handle the coroutine viewModelScope with coroutineExceptionHandler
     */

    open val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            _state.emit(
                BaseViewState.Error(
                    ResultException(
                        cause = exception
                    )
                )
            )
        }
    }

    fun launchCoroutine(
        coroutineExceptionHandler: CoroutineExceptionHandler,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }
}
