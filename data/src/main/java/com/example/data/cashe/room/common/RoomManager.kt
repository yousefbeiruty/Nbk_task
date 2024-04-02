package com.example.data.cashe.room.common

import com.example.data.cashe.manager.BaseRepository
import com.example.data.cashe.room.features.news.entities.FavoriteNewsEntity
import com.example.domain.common.ResultWrapper
import com.example.data.cashe.utils.safeLocalDataCall
import javax.inject.Inject

class RoomManager @Inject constructor(private val databaseRoom: DatabaseRoom) : BaseRepository {
    override suspend fun getFavouriteTopNews(): ResultWrapper<List<FavoriteNewsEntity>>? =
        safeLocalDataCall { databaseRoom.newsDao().getFavoriteNews() }

    override suspend fun insertToFavouriteNews(favoriteNewsEntity: FavoriteNewsEntity): ResultWrapper<Unit> =
        safeLocalDataCall { databaseRoom.newsDao().insert(favoriteNewsEntity) }

}