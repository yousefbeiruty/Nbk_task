package com.example.data.network.movie

import com.example.data.network.common.NetworkException
import com.example.data.network.common.NetworkResult
import com.example.data.network.extensions.toNetworkResult
import com.example.data.network.services.ApiManager
import com.example.navigationtutorial.model.MoviesListResponse
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
class MovieServiceTest {

    @Mock
    private lateinit var apiManager: ApiManager
    @InjectMocks
    private lateinit var movieService: MovieService

    @Before
    fun setup() {
       // MockitoAnnotations.initMocks(this)
      //  movieService = MovieService(apiManager)
    }

    @Test
    fun testGetMostPopularSuccess() = runBlocking {
            // Arrange
            val mockMovieResult = MoviesListResponse.Result(
                id = 453395,
                title = "title",
                originalTitle = "originalTitle",
                originalLanguage = "originalLanguage",
                adult = false,
                backdropPath = "backdropPath",
                genreIds = listOf(1, 2, 3),
                overview = "overview",
                popularity = 7931.499,
                posterPath = "9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg",
                releaseDate = "2022-05-04",
                video = false,
                voteAverage = 7.5,
                voteCount = 3987
            )
        val expectedResult = listOf(mockMovieResult)
        val response = Response.success(MoviesListResponse(1, expectedResult))

        Mockito.`when`(apiManager.getMovies<MoviesListResponse>(Mockito.anyString()))
            .thenReturn(safeApiCallList { response })

        // Act
        val result = movieService.getMostPopular("test_key", 1, "popularity.desc")

        // Assert
        Assert.assertTrue(result is NetworkResult.Success)
        Assert.assertEquals(expectedResult, (result as NetworkResult.Success).data)

        // Verify method call
        Mockito.verify(apiManager).getMovies<MoviesListResponse>("https://api.themoviedb.org/3/discover/movie?api_key=9e851da311d976ec3754a43b0185bc8c&page=1&sort_by=popularity.desc")

    }

    @Test
    fun testGetMostPopularError()= runBlocking {

            // Arrange
            val errorResponse = Response.error<MoviesListResponse>(400, "Bad Request".toResponseBody())
            Mockito.`when`(apiManager.getMovies<MoviesListResponse>(Mockito.anyString()))
                .thenReturn(safeApiCallList {
                    errorResponse
                })

            // Act
            val result = movieService.getMostPopular("test_key", 1, "popularity.desc")

            // Assert
            Assert.assertTrue(result is NetworkResult.Error)
            // You can further assert the error state if needed
    }
}
inline fun safeApiCallList(
    apiCall: () -> Response<MoviesListResponse>
): NetworkResult<List<MoviesListResponse.Result>> {
    return runCatching {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                NetworkResult.Success(body.results) // Assuming MoviesListResponse has a property called 'results'
            } else {
                NetworkResult.Error(NetworkException())
            }
        } else {
            NetworkResult.Error(NetworkException())
        }
    }.getOrElse {
        NetworkResult.Error(NetworkException())
    }
}