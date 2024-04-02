package com.example.nbktask.compose.screens.details

import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.domain.model.topheadline.ArticleHeadLine
import com.example.nbktask.R
import com.example.nbktask.compose.sharViewModel.SharedViewModel
import com.example.nbktask.compose.ui.theme.myColors
import com.example.nbktask.utils.categorizeTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(sharedViewModel: SharedViewModel= hiltViewModel(),detailsViewModel: DetailsViewModel = hiltViewModel()) {
    val article=sharedViewModel.getArticleDetails()
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
                    FavoriteButton(article,detailsViewModel)
                }
            }
}

@Composable
fun FavoriteButton(articleHeadLine: ArticleHeadLine,detailsViewModel: DetailsViewModel = hiltViewModel()) {
  val context= LocalContext.current
    Button(modifier = Modifier.padding(10.dp),
        onClick = {
            detailsViewModel.insertToFavouriteNews(articleHeadLine)
            Toast.makeText(context,"Added To Favourite",Toast.LENGTH_LONG).show()
                  },
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor =MaterialTheme.myColors.Yellow

        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
              Icons.Default.Favorite ,
                contentDescription = null // to indicate no content description for the icon
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(stringResource(R.string.add_to_favorites))
        }
    }
}

@Composable
fun ArticleImage(url: String) {
    val painter: Painter = rememberAsyncImagePainter(url)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ArticleTitle(title: String, time: String) {
    Box (contentAlignment = Alignment.BottomCenter){
        Card(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(MaterialTheme.myColors.Gray)
        ) {
            Column {
                Text(
                    text = title,
                    color = MaterialTheme.myColors.Black,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = categorizeTime(time),
                    color = MaterialTheme.myColors.texColor,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }

}

@Composable
fun ArticleDetails(details: String) {
    Text(
        text = details,
        style = MaterialTheme.typography.titleMedium,
        color=MaterialTheme.myColors.texColor,
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 10.dp)
    )
}

