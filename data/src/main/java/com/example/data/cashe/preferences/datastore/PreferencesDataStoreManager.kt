package com.example.data.cashe.preferences.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.data.cashe.manager.BaseRepository
import com.example.data.cashe.utils.catchException
import com.example.data.cashe.utils.setNullableValue
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataStoreManager @Inject constructor(@ApplicationContext context: Context) :
    BaseRepository {

    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        ReplaceFileCorruptionHandler { emptyPreferences() },
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        migrations = listOf(SharedPreferencesMigration(context, SHARED_PREFERENCES_NAME)),
        produceFile = { context.preferencesDataStoreFile(PREFERENCES_DATA_STORE_NAME) })


    override suspend fun setLoggedInUserEmail(value: String?) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences.setNullableValue(
                PreferencesDataStoreKeys.loggedInUserFullName,
                value
            )
        }
    }

    override fun getLoggedInUserEmail(): Flow<String?> {
        return dataStore.data.catchException()
            .map { preferences -> preferences[PreferencesDataStoreKeys.loggedInUserFullName] }
    }

    companion object {

        const val SHARED_PREFERENCES_NAME = "AppPreferencesManager"
        const val PREFERENCES_DATA_STORE_NAME = "NyTimePreferences"
    }
}
