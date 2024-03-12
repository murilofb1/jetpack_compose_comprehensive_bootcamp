package com.example.movieapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.components.BlurredImage
import com.example.movieapp.components.ButtonSimple
import com.example.movieapp.components.IconButtonSimple
import com.example.movieapp.components.TextDetailTitle
import com.example.movieapp.components.TopBarSimple
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieModel

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    if (movieId == null) throw NullPointerException("Movie ID cannot be null")

//    val movie = MovieModel.getItemById(movieId)!!
    val movie = Movie.getMovies().find { movieId == it.id }
    /*
        Box(modifier = Modifier.fillMaxSize()) {
            MovieBG(movieBackgroundId = movie.getImageId())
            TextDetailTitle(text = movie.getName())
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                ButtonSimple(onClick = { navController.popBackStack() }, "voltar")
            }

        }*/
    Scaffold(
        topBar = {
            TopBarSimple(
                title = "Movie Details",
                navigationIcon = {
                    IconButtonSimple(
                        onClick = { navController.popBackStack() },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            MovieBG(imageLink = movie!!.poster)
//            TextDetailTitle(text = movie.getName())

            TextDetailTitle(text = movie.title)
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                ButtonSimple(onClick = { navController.popBackStack() }, "voltar")
            }

        }
    }

}


/*
@Composable
fun MovieBG(movieBackgroundId: Int) {
    BlurredImage(
        modifier = Modifier.fillMaxSize(),
        imageId = movieBackgroundId,
    )
}
*/
@Composable
fun MovieBG(imageLink: String) {
    BlurredImage(
        modifier = Modifier.fillMaxSize(),
        imageLink = imageLink
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    DetailsScreen(
        navController = rememberNavController(),
        movieId = MovieModel.defaultList[0].getId()
    )
}