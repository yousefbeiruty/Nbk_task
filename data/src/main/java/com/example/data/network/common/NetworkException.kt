package com.example.data.network.common

import com.example.data.R
import com.example.domain.common.ResultException

open class NetworkException(
    messageResource: Int = R.string.error_unexpected,
    cause: Throwable? = null
) : ResultException(messageResource, cause)

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T?) : NetworkResult<T>()

    data class Error(val error: NetworkException) : NetworkResult<Nothing>()

}