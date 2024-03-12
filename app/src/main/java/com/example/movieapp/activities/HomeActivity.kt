@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.navigation.MovieNavigation
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodels.HomeViewModel

class HomeActivity : ComponentActivity() {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(showTopBar: Boolean) {

}
/*

@Composable
fun Myapp(viewModel: HomeViewModel) {
    MovieAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar("Movies") }
        ) {
            MainContent(
                scaffoldPadding = it,
                HomeViewModel.movieList
            )
        }
    }

}
*/
/*
@Composable
fun MainContent(
    scaffoldPadding: PaddingValues,
    list: List<MovieModel>
) {
    Column(modifier = Modifier.padding(scaffoldPadding)) {
        LazyColumn(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
            items(list) { movie ->
                MovieRow(movie = movie)
            }
        }
    }
}*/


@Preview(showBackground = true)
@Composable
fun ActivityPreview() {

    MovieNavigation()
}