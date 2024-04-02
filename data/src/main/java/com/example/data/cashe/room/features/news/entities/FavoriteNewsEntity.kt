package com.example.data.cashe.room.features.news.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FavoriteNewsEntity")
data class FavoriteNewsEntity(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @SerializedName("author")
    val author: String?=null,
    @SerializedName("content")
    val content: String?=null,
    @SerializedName("description")
    val description: String?=null,
    @SerializedName("publishedAt")
    val publishedAt: String?=null,
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("url")
    val url: String?=null,
    @SerializedName("urlToImage")
    val urlToImage: String?=null
)