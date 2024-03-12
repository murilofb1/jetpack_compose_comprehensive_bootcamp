package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.components.AsyncImageRecycler
import com.example.movieapp.components.TopBarSimple
import com.example.movieapp.models.Movie
import com.example.movieapp.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBarSimple("Movies") }
    ) {
        MainContent(
            navController = navController,
            scaffoldPadding = it,
            list = Movie.getMovies()
        )
    }
}

/*
@Composable
fun MainContent(
    navController: NavController,
    scaffoldPadding: PaddingValues,
    list: List<MovieModel>
) {
    Column(modifier = Modifier.padding(scaffoldPadding)) {
        RecyclerList(list = list) { movieId ->
            navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movieId")
        }
    }
}
*/
@Composable
fun MainContent(
    navController: NavController,
    scaffoldPadding: PaddingValues,
    list: List<Movie>
) {
    Column(modifier = Modifier.padding(scaffoldPadding)) {
        AsyncImageRecycler(list = list) { movieId ->
            navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movieId")
        }
    }
}


@Preview
@Composable
fun PreviewHome() {
    HomeScreen(navController = rememberNavController())
}