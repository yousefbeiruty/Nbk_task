package com.example.nbktask.common

import com.example.domain.common.ResultException

sealed class BaseViewState  {

    /**
     * Idle
     */
    object Idle : BaseViewState()

    /**
     * Loading
     */
    object Loading : BaseViewState()

    /**
     * Error when loading data.
     */
    data class Error(val resultException: ResultException) : BaseViewState()

    /**
     * Data successfully loaded.
     */
    object DataLoaded : BaseViewState()

}
