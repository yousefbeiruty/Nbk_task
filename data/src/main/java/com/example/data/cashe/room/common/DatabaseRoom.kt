package com.example.data.cashe.room.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.cashe.room.features.news.dao.NewsDao
import com.example.data.cashe.room.features.news.entities.FavoriteNewsEntity

@Database(
    entities = [FavoriteNewsEntity::class],
    version = RoomConstants.DATABASE_VERSION
)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun newsDao():NewsDao
}