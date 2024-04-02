package com.example.nbktask.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nbktask.R
import com.example.nbktask.compose.bottomnav.BottomNavigation
import com.example.nbktask.compose.nav_graph.NavigationGraph
import com.example.nbktask.compose.sharViewModel.SharedViewModel
import com.example.nbktask.compose.ui.theme.DeloiteTaskTheme
import com.example.nbktask.compose.ui.theme.myColors
import com.example.nbktask.compose.ui.theme.myTypography
import com.example.nbktask.utils.listOfCategories
import com.example.nbktask.utils.mapOfCountries
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeloiteTaskTheme(darkTheme = isSystemInDarkTheme()) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   MainScreenView()
                }
            }
        }
    }
}




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(sharedViewModel: SharedViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    androidx.compose.material.Scaffold(topBar = {
        TopAppBarSample(
            navController,
            sharedViewModel
        )
    }, bottomBar = {
        if (sharedViewModel.flag) BottomNavigation(navController = navController)
    }) {
        NavigationGraph(navController = navController)
    }
}

@Composable
fun TopAppBarSample(navController: NavHostController, sharedViewModel: SharedViewModel) {
    var flag=sharedViewModel.flag
    var showDialog by remember { mutableStateOf(false) }
    Column {
        androidx.compose.material.TopAppBar(elevation = 4.dp, title = {
            Text(
                stringResource(R.string.nbk), style =
                MaterialTheme.myTypography.bigText
            )
        }, backgroundColor = MaterialTheme.myColors.Purpple, navigationIcon = {
            androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {
                if (!flag) androidx.compose.material.Icon(
                    Icons.Filled.ArrowBack, null,
                    tint = Color.White
                )
            }
        }, actions = {
            if (flag)androidx.compose.material.IconButton(onClick = {showDialog = true }) {
                androidx.compose.material.Icon(Icons.Filled.Share, null,)
            }
        })
        // Conditionally render the dialog based on the showDialog state
        if (showDialog) {
            CustomDialogScreen(onDismissRequest = { showDialog = false },sharedViewModel)
        }
    }
}
@Composable
fun CustomDialogScreen(onDismissRequest: () -> Unit, sharedViewModel: SharedViewModel) {
    var selectedCountry by remember { mutableStateOf<String?>("USA") }
    var selectedCategory by remember { mutableStateOf<String?>("general") }
    val countries = arrayListOf<String>()
    mapOfCountries.map {
        countries.add(it.key)
    }
            Dialog(
                onDismissRequest =onDismissRequest
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.myColors.white,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(bottom = 40.dp)
                    ) {
                        Text(text = stringResource(R.string.select_category),style =
                        MaterialTheme.myTypography.smallText)
                        TextsWithRadioButtons(listOfCategories) {
                            selectedCategory = it
                        }
                        Text(text = stringResource(R.string.select_country), style =
                        MaterialTheme.myTypography.smallText)
                        TextsWithRadioButtons(countries){
                            selectedCountry=it
                        }

                    }
                    Button(
                        onClick ={
                            sharedViewModel.setValueFilter(true)
                            sharedViewModel.setValueCategory(selectedCategory.toString())
                            sharedViewModel.setValueCountry(mapOfCountries.getValue(selectedCountry.toString()))
                            onDismissRequest()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.myColors.Purpple)
                                ,modifier = Modifier
                            .align(Alignment.BottomCenter) // Align to the bottom center
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.done))
                    }
        }
    }
}

@Composable
fun TextsWithRadioButtons(texts: List<String>,getSelected:(String)->Unit) {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    LazyColumn {
        items(texts.size) { index ->
            TextWithRadioButton(
                text = texts[index],
                selectedOption = selectedOption,
                onOptionSelected = {
                    selectedOption = it
                    getSelected(it)
                }
            )
        }
    }
}


@Composable
fun TextWithRadioButton(text: String, selectedOption: String?, onOptionSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        RadioButton(
            selected = (selectedOption == text),
            onClick = { onOptionSelected(text) },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Blue // Customize selected color
            )
        )
        Text(text = text, style =  MaterialTheme.myTypography.smallText)
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DeloiteTaskTheme {
        Greeting("Android")
    }
}