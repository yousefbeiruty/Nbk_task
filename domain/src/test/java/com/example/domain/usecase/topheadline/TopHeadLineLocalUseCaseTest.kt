package com.example.domain.usecase.topheadline

import com.example.domain.common.ResultWrapper
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.repository.topheadline.TopNewsLocalRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class TopHeadLineLocalUseCaseTest {

    private lateinit var localRepository: TopNewsLocalRepository
    private lateinit var useCase: TopHeadLineLocalUseCase

    @Before
    fun setUp() {
        localRepository = mock()
        useCase = TopHeadLineLocalUseCase(localRepository)
    }

    @Test
    fun `invoke should return flow from local repository`() = runBlocking {
        val expectedFlow = flow<ResultWrapper<List<ArticleHeadLine>?>> { }
        whenever(localRepository.getFavouriteTopNews()).thenReturn(expectedFlow)

        val resultFlow = useCase.invoke(null)

        assertEquals(expectedFlow.toList(), resultFlow.toList())
    }

    @Test
    fun `insertFavouriteNews should insert article`() = runBlocking {
        val article = ArticleHeadLine("author", "content", "description")
        val expected = ResultWrapper.Success<Unit?>(null)

        whenever(localRepository.insertFavouriteTopNews(any())).thenReturn(flow {
            emit(expected)
        })


        useCase.insertFavouriteNews(article)
    }
}