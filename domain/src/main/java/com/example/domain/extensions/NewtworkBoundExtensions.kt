package com.example.domain.extensions


import com.example.domain.common.ResultException
import com.example.domain.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * GENERIC TYPES
 * This is a generic function and that means it can work with any type of data,
 * @param ResultType is the data type loaded the data from the local cache . Can be any thing,
 * a list or any object.
 * @param RequestType is the data type loaded from the network. Can be any thing,
 * a list or any object.
 * @see networkBoundResource
 *
 * ARGUMENT PARAMETERS
 * This function takes in four argument parameters which are functions
 *
 * NOTE!!! -> all the parameters are function implementations of the following pieces of logic
 *
 * @param queryDb
 * @return Flow<ResultType>
 * pass in a function that loads data from your local cache and returns a flow of your
 * specified data type <ResultType>
 *
 * @param fetchApi
 * @return Flow<RequestType>
 * pass in a function, a suspend function, that loads data from your rest api and returns
 * an object of <RequestType>
 *
 * @param saveApiResult
 * @return Unit
 * pass in a function that just takes in Flow<RequestType> (The data type got from the network)
 * and saves it in the local cache.
 *
 * @param shouldFetchApi
 * @return Boolean
 * pass in a function that has the logic to whether the algorithm should make
 * a networking call or not.
 * In this case, this function takes in data loaded from @param query and determines whether
 * to make a networking call or not.
 * This can vary with your implementation however, say fetch depending on the last time
 * you made a networking call....e.t.c.
 *
 *
 * @param onQueryDbError
 * @return Flow<ResultType>
 * pass in a function that holds the response of the api to retrieve the api response in case
 * any exception happened in the caching module
 *
 */
fun <ResultType, RequestType> networkBoundResource(
    queryDb: () -> Flow<ResultType>,
    fetchApi: suspend () -> Flow<RequestType>,
    saveApiResult: suspend (Flow<RequestType>) -> Unit,
    shouldFetchApi: (ResultType) -> Boolean = { true },
    onQueryDbError: () -> Flow<ResultType>,
) = flow {

    //First step, fetch data from the local cache
    val data = queryDb().first()
    //If we set shouldFetch to false, we just receive the data from the database without
    // requesting the API.
    //If shouldFetch returns true,
    if (shouldFetchApi(data)) {
        //make a networking call and save it to the database
        saveApiResult(fetchApi())
    }
    //Now fetch data again from the database and Dispatch it to the UI
    val flow = queryDb()

    //here we check If the database is empty or there is an error, we retrieve the api data
    //and display it on the UI.
    if (data != null && data !is ResultWrapper.Error)
        emitAll(flow)
    else {
        emitAll(onQueryDbError())
    }
}

suspend fun <T> resultWrapperData(
    resultWrapper: ResultWrapper<T>,
    success:suspend (T) -> Unit,
    error: (ResultException) -> Unit
) {
    when (resultWrapper) {
        is ResultWrapper.Success -> {
            success(resultWrapper.data)
        }
        is ResultWrapper.Error -> {
            error(resultWrapper.error)
        }

        else -> {}
    }
}