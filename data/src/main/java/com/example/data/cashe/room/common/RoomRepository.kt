package com.example.data.cashe.room.common

import com.example.data.cashe.room.features.news.entities.FavoriteNewsEntity
import com.example.domain.common.ResultWrapper

interface RoomRepository {
    suspend fun getFavouriteTopNews(): ResultWrapper<List<FavoriteNewsEntity>>? = null

    suspend fun insertToFavouriteNews(favoriteNewsEntity:FavoriteNewsEntity): ResultWrapper<Unit>? = null
}