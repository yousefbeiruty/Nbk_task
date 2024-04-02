package com.example.data.cashe.manager

import com.example.data.cashe.preferences.datastore.PreferencesRepository
import com.example.data.cashe.room.common.RoomRepository

interface  BaseRepository : RoomRepository, PreferencesRepository