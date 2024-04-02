package com.example.domain.model.topheadline

import com.google.gson.annotations.SerializedName

data class ArticleHeadLine(
    val author: String?=null,
    val content: String?=null,
    val description: String?=null,
    val publishedAt: String?=null,
    val title: String?=null,
    val url: String?=null,
    val urlToImage: String?=null
)
