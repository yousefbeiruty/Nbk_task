package com.example.data.cashe.room.common

import android.content.Context
import androidx.room.Room

object RoomClient {

    fun createDatabaseRoom(
        context: Context
    ): DatabaseRoom = Room
        .databaseBuilder(context, DatabaseRoom::class.java, RoomConstants.DATABASE_NAME)
        .allowMainThreadQueries()
        .build()

}