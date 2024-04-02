package com.example.data.network.common

import com.example.data.cashe.room.features.news.entities.FavoriteNewsEntity
import com.example.domain.model.topheadline.ArticleHeadLine


internal fun ArticleHeadLine.toFavoriteNewsEntity():FavoriteNewsEntity{
    return FavoriteNewsEntity(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}


internal fun List<FavoriteNewsEntity>.toArticleHeadLine():List<ArticleHeadLine>{
    val favouriteNewsList = arrayListOf<ArticleHeadLine>()

    this.map {
        favouriteNewsList.add(ArticleHeadLine(
             it.author,
            it.content,
            it.description,
            it.publishedAt,
            it.title,
            it.url,
            it.urlToImage
        ))
    }
    return favouriteNewsList
}
