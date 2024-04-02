package com.example.domain.common

sealed class ResultWrapper<out T : Any?> {

    data class Success<out T : Any?>(val data: T) : ResultWrapper<T>()

    data class Error(val error: ResultException) : ResultWrapper<Nothing>()
}

open class ResultException(
    val messageResource: Int? = null,
    cause: Throwable? = null
): RuntimeException(cause)
