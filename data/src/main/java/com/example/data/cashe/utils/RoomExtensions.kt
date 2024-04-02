package com.example.data.cashe.utils

import com.example.data.R
import com.example.data.cashe.room.features.news.entities.FavoriteNewsEntity
import com.example.domain.common.ResultException
import com.example.domain.common.ResultWrapper

/**
 * Use this function to handle try, catch queries and mapping the result to ResultWrapper
 */
inline fun <T> safeLocalDataCall(
    queryCall: () -> T
): ResultWrapper<T> {
    return runCatching {
        queryCall()
    }.toResultWrapper()
}

/**
 * Use toResultWrapper for mapping the result to ResultWrapper
 */
fun <T> Result<T>.toResultWrapper(): ResultWrapper<T> = fold(
    onSuccess = { result ->
        ResultWrapper.Success(result)
    },
    onFailure = { error ->
        val errorModel = error as ResultException
        ResultWrapper.Error(errorModel)
    }
)

/**
 * tryMapperQuery is a generic function take DATA and DOMAIN generic parameter
 * @param DATA is the entity model that cached in the database
 * @param DOMAIN is the model that we went to mapping it to domain layer
 *
 *  @param query
 * @return ResultWrapper<DATA>?
 * pass in a suspend function that holds the result data from cached module
 *
 *  @param entityToDomain
 * @return DOMAIN
 * pass in a function that mapped the entity to domain model
 */
suspend fun <DATA, DOMAIN> tryMapperQuery(
    query: suspend () -> ResultWrapper<DATA>?,
    entityToDomain: (DATA?) -> DOMAIN?
) = try {
    when (val data = query()) {
        is ResultWrapper.Success -> {
            ResultWrapper.Success(entityToDomain(data.data))
        }
        is ResultWrapper.Error -> {
            ResultWrapper.Error(data.error)
        }
        else -> {
            ResultWrapper.Error(parseErrorEntity())
        }
    }
} catch (e: Exception) {
    ResultWrapper.Error(parseErrorEntity())
}

/**
 * Use parseErrorEntity for mapping ResultException error
 */
fun parseErrorEntity(): ResultException {
    return mapRoomError()
}

/**
 * here we do the mapping for cache room database
 */
private fun mapRoomError(): ResultException =
    ResultException(R.string.caching_room_error)
