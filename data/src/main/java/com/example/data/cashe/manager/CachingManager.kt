package com.example.data.cashe.manager

import com.example.data.cashe.preferences.datastore.PreferencesDataStoreManager
import com.example.data.cashe.room.common.RoomManager

class CachingManager(
    private val preferencesDataStoreManager: PreferencesDataStoreManager,
    private val roomManager: RoomManager
) {
    // The getProvider method is to choose what caching manager you want to provide.
    // The provider parameter is an enum class that includes every type of caching.
    fun getProvider(provider: ProviderEnum): BaseRepository {
        return when (provider) {
            ProviderEnum.ROOM -> return roomManager
            ProviderEnum.PREFERENCES -> preferencesDataStoreManager
        }
    }
}