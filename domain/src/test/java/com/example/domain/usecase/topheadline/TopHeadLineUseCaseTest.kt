package com.example.domain.usecase.topheadline

import androidx.paging.PagingData
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.repository.topheadline.TopHeadLineRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
class TopHeadLineUseCaseTest{
    @Mock
    private lateinit var repository: TopHeadLineRepository

    private lateinit var useCase: TopHeadLineUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = TopHeadLineUseCase(repository)
    }

    @Test
    fun `invoke should return flow of paging data`() = runBlocking {
        // Given
        val testData = PagingData.from(listOf(ArticleHeadLine("Title 1"), ArticleHeadLine("Title 2")))
        val country = "us"
        val category = "business"
        `when`(repository.getTopHeadLineFilter(country, category)).thenReturn(flowOf(testData))

        // When
        val result = useCase.getTopHeadLineByFilter(country, category).toList()

        // Then
        Assert.assertEquals(testData, result.single())
    }

    @Test
    fun `getTopHeadLineByFilter should return flow of paging data based on filters`() = runBlocking {
        // Given
        val testData = PagingData.from(listOf(ArticleHeadLine("Title 1"), ArticleHeadLine("Title 2")))
        val country = "us"
        val category = "business"
        `when`(repository.getTopHeadLineFilter(country, category)).thenReturn(flowOf(testData))

        // When
        val result = useCase.getTopHeadLineByFilter(country, category).toList()

        // Then
        Assert.assertEquals(testData, result.single())
    }
}