package com.example.data.di

import android.content.Context
import com.example.data.cashe.manager.CachingManager
import com.example.data.cashe.preferences.datastore.PreferencesDataStoreManager
import com.example.data.cashe.room.common.DatabaseRoom
import com.example.data.cashe.room.common.RoomClient
import com.example.data.cashe.room.common.RoomManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CachingModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DatabaseRoom {
        return RoomClient.createDatabaseRoom(appContext)
    }

    @Provides
    @Singleton
    fun provideCachingManager(
        preferencesDataStoreManager: PreferencesDataStoreManager,
        roomManager: RoomManager
    ): CachingManager {
        return CachingManager(preferencesDataStoreManager, roomManager)
    }
}