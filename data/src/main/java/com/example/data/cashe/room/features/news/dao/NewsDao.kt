package com.example.data.cashe.room.features.news.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.cashe.room.common.BaseDao
import com.example.data.cashe.room.features.news.entities.FavoriteNewsEntity

@Dao
interface NewsDao: BaseDao<FavoriteNewsEntity> {
    @Query("SELECT * FROM FavoriteNewsEntity")
    fun getFavoriteNews(): List<FavoriteNewsEntity>
}