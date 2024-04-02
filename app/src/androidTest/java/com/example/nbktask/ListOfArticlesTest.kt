package com.example.nbktask


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.domain.repository.topheadline.TopNewsLocalRepository
import com.example.domain.usecase.topheadline.TopHeadLineLocalUseCase
import com.example.nbktask.compose.screens.details.ArticleDetails
import com.example.nbktask.compose.screens.details.ArticleImage
import com.example.nbktask.compose.screens.details.ArticleTitle
import com.example.nbktask.compose.screens.details.DetailsViewModel
import com.example.nbktask.compose.screens.details.FavoriteButton
import com.example.nbktask.compose.screens.home.listofArticles
import com.example.nbktask.compose.sharViewModel.SharedViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.*
import io.mockk.*
import io.mockk.impl.annotations.MockK

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ListOfArticlesTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @MockK
    lateinit var viewModel: DetailsViewModel
    @MockK
    lateinit var sharedViewModel :SharedViewModel
    // Initialize localRepository and useCase directly
    @MockK
    lateinit var repositoryMock: TopNewsLocalRepository

    private lateinit var useCase: TopHeadLineLocalUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = TopHeadLineLocalUseCase(repositoryMock)
        viewModel = DetailsViewModel(useCase)
        sharedViewModel= SharedViewModel()
    }
    @Test
    fun listofArticles_WhenEmptyList_ShowEmptyState() {
       // Given an empty list of articles
        val articleHeadLine = emptyList<ArticleHeadLine>()
        val searchText = "Search Query"

        // Calculate the value needed for listofArticles outside the remember block
        val lazyPagingItems = Pager(PagingConfig(pageSize = 10)) {
            mockPagingSource(articleHeadLine)
        }.flow

        // When the Composable function is invoked
        composeTestRule.setContent {
            val navController = rememberNavController()
            listofArticles(
                articleHeadLine = lazyPagingItems.collectAsLazyPagingItems(),
                searchText = searchText,
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    }
    fun mockPagingSource(articleHeadLine: List<ArticleHeadLine>): PagingSource<Int, ArticleHeadLine> {
        return object : PagingSource<Int, ArticleHeadLine>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleHeadLine> {
                return LoadResult.Page(
                    data = articleHeadLine,
                    prevKey = null,
                    nextKey = null
                )
            }

            override fun getRefreshKey(state: PagingState<Int, ArticleHeadLine>): Int? {
                return null
            }
        }
    }

    @Test
    fun lazyColumn_ShowArticleComponents() {
        // Given a mock article and view model
        val article = ArticleHeadLine("Title", "Description", "Url")

        // When the LazyColumn Composable is rendered
        composeTestRule.setContent {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    ArticleImage(article.urlToImage.toString())
                }
                item {ArticleTitle(article.title.toString(),article.publishedAt.toString())}
                item {
                    ArticleDetails(article.description.toString())
                }
                item {
                    FavoriteButton(article,viewModel)
                }
            }
        }
    }
}