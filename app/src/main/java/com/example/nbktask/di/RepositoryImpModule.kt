package com.example.nbktask.di

import com.example.data.cashe.manager.CachingManager
import com.example.data.network.newsheadlines.NewsHeadLinesService
import com.example.data.network.repository.newsheadline.TopHeadLineRemoteDataRepositoryImpl
import com.example.data.network.repository.newsheadline.TopHeadLineRepositoryImpl
import com.example.data.network.repository.newsheadline.TopNewsLocalRepositoryImpl
import com.example.domain.repository.topheadline.TopHeadLineRemoteDataRepository
import com.example.domain.repository.topheadline.TopHeadLineRepository
import com.example.domain.repository.topheadline.TopNewsLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryImpModule {
    @Provides
    @ViewModelScoped
    fun getTopHeadLineLocalRepository(cachingManager: CachingManager):TopNewsLocalRepository{
        return TopNewsLocalRepositoryImpl(cachingManager)
    }

    @Provides
    @ViewModelScoped
    fun getTopHeadLineRepository(newsHeadLinesService: NewsHeadLinesService): TopHeadLineRemoteDataRepository {
        return TopHeadLineRemoteDataRepositoryImpl(newsHeadLinesService)
    }

    @Provides
    @ViewModelScoped
    fun providesTopHeadLineRepository(topHeadLineRemoteDataSource: TopHeadLineRemoteDataRepository): TopHeadLineRepository {
        return TopHeadLineRepositoryImpl(topHeadLineRemoteDataSource)
    }

}