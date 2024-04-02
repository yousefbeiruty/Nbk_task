package com.example.data.cashe.preferences.datastore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface PreferencesRepository {


    suspend fun setLoggedInUserEmail(value: String?){}
    fun getLoggedInUserEmail(): Flow<String?> {
        return emptyFlow()
    }

}