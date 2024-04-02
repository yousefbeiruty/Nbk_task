package com.example.data.network.extensions


import com.example.data.network.newsheadlines.model.TopHeadLineNewsResponse
import com.example.domain.model.topheadline.ArticleHeadLine


internal fun List<TopHeadLineNewsResponse.Article>.toArticleHeadLine():List<ArticleHeadLine>{
    val articles= arrayListOf<ArticleHeadLine>()

    this.forEach{
        articles.add(
            ArticleHeadLine(
                 it.author,
                it.content,
                it.description,
                it.publishedAt,
                it.title,
                it.url,
                it.urlToImage
            )
        )
    }
    return articles
}

