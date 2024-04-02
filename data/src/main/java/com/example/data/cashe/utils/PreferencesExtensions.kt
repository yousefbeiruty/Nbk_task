package com.example.data.cashe.utils

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

fun Flow<Preferences>.catchException(): Flow<Preferences> {
    return catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }
}

/**
 * since on preferences data store there is no way to set value to null
 * This function is used to remove preference when we need to set it null
 */
fun <T> MutablePreferences.setNullableValue(
    key: Preferences.Key<T>,
    value: T?
) {
    if (value == null) {
        if (contains(key)) {
            remove(key)
        }
    } else {
        this[key] = value
    }
}