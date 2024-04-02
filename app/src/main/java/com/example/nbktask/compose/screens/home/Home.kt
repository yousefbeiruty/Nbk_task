package com.example.nbktask.compose.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.nbktask.R
import com.example.nbktask.compose.bottomnav.Screen
import com.example.nbktask.compose.sharViewModel.SharedViewModel
import com.example.nbktask.utils.ErrorMessage
import com.example.nbktask.utils.LoadingNextPageItem
import com.example.nbktask.utils.PageLoader
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.nbktask.compose.ui.theme.myColors
import com.example.nbktask.utils.EmptyList
import com.example.nbktask.utils.categorizeTime
import com.example.nbktask.utils.isNetworkAvailable
import kotlin.math.roundToInt

@Composable
fun HomeScreen(navController: NavController,
               viewModel: HomeViewMode = hiltViewModel(),
               sharedViewModel: SharedViewModel= hiltViewModel()
) {
    val articlePagingItems: LazyPagingItems<ArticleHeadLine> = viewModel.articleState.collectAsLazyPagingItems()
     if(sharedViewModel.filtering){
         viewModel.getArticleHeadLine(sharedViewModel.country,sharedViewModel.category)
     }
     if(!isNetworkAvailable(LocalContext.current))
         EmptyList(stringResource(R.string.there_is_no_internet_connection))
    SearchableLazyColumn(articles = articlePagingItems, // Replace with your list of items
       navController,
        sharedViewModel,
        onSearchTextChanged = { newText ->
            // Handle search text changes here
        }, onItemClick = { selectedItem ->
            // Handle item click here
        })
}
@Composable
fun SearchableLazyColumn(
    articles:  LazyPagingItems<ArticleHeadLine>, // Replace with your data type
    navController: NavController,
    sharedViewModel: SharedViewModel,
    onSearchTextChanged: (String) -> Unit, // Callback for search text changes
    onItemClick: (String) -> Unit,// Callback for item click,
    isError: Boolean = false
) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
    ) {
        // Search bar
        TextField(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isError) Color(0xFFC4C2C2) else Color.LightGray, // Background color with error state
                shape = RoundedCornerShape(8.dp) // Rounded corners
            ),
            value = searchText,
            onValueChange = {
                searchText = it
                onSearchTextChanged(it)
            },
            label = {
                //  Text(text = "Search")
            },
            singleLine = true, // Display as a single-line input
            textStyle = TextStyle(
                color = if (isError) Color.Red else Color.Black, // Text color with error state
                fontSize = 14.sp
            ),
            placeholder = { Text(text = "Search") },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            maxLines = 1,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null, // Decorative content, so we set it to null
                    tint = Color.Gray, // Customize the icon color
                    modifier = Modifier.padding(8.dp)
                )
            }
            // Set the placeholder text
        )
        listofArticles(articles, searchText,navController,sharedViewModel)
    }
}
@Composable
fun listofArticles(
    articleHeadLine: LazyPagingItems<ArticleHeadLine>,
    searchText: String,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp,),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }

        val filteredList = articleHeadLine.itemSnapshotList.filter {
            it?.title?.contains(searchText, ignoreCase = true) == true
        }

        items(filteredList.count()) { index ->
            Box(contentAlignment = Alignment.Center) {
                CardDemo(filteredList[index],navController,sharedViewModel)
            }
        }

        articleHeadLine.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = articleHeadLine.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = articleHeadLine.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(4.dp)) }
        }
        item { Spacer(modifier = Modifier.padding(30.dp)) }
    }
}
@Composable
fun CardDemo(obj: ArticleHeadLine?, navController: NavController, sharedViewModel: SharedViewModel) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable {
                obj?.let { sharedViewModel.setArticleDetails(it) }
                navController.navigate(Screen.ArticleDetailsScreen.route)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row( modifier = Modifier
            .fillMaxWidth(),) {
            NetworkImage(
                contentDescription = "",
                url = obj?.urlToImage.toString(),
                width = 150,
                height = 170
            )
            Column( verticalArrangement = Arrangement.Bottom) {
                TextWithEllipsis(obj?.title.toString(), 100)
                Text(
                    text = categorizeTime(obj?.publishedAt.toString()),
                    color = MaterialTheme.myColors.texColor,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 16.dp)
                        .align(Alignment.Start)
                )
            }
        }
    }
}

@Composable
fun TextWithEllipsis(text: String, maxLength: Int) {
    var truncatedText by remember { mutableStateOf(text) }

    if (text.length > maxLength) {
        truncatedText = text.substring(0, maxLength - 2) + ".."
    }
    Text(
        text = truncatedText,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.myColors.Black,
        fontSize = 15.sp,
        modifier = Modifier.padding(5.dp),
    )
}

@Composable
fun NetworkImage(url: String, contentDescription: String?, width: Int, height: Int) {
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current

    val scrollState = rememberScrollState()
// Access the current scroll position using scrollState.value
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.ic_movie)
                }).build()
            ),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(width.dp)
                .height(height.dp)
                .offset {
                    IntOffset(
                        x = (width * scrollState.value / (scrollState.maxValue - scrollState.value) * 0.5f).roundToInt(),
                        y = 0
                    )
                },
        )
    }
}
