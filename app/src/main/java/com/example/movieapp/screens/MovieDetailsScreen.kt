package com.example.movieapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.movieapp.components.AsyncMovieImage
import com.example.movieapp.components.AsyncBlurredImage
import com.example.movieapp.components.IconButtonSimple
import com.example.movieapp.components.TextDetailInfo
import com.example.movieapp.components.TextDetailTitle
import com.example.movieapp.components.TopBarSimple
import com.example.movieapp.models.Movie

lateinit var movie: Movie

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    if (movieId == null) throw NullPointerException("Movie ID cannot be null")
    movie = Movie.getMovies().find { movieId == it.id }!!

    DetailsBG(navController) {
        MovieBG()
        Column(modifier = Modifier.fillMaxSize()) {
            TextDetailTitle(text = movie.title)
            FilmPoster()
            TextDetailInfo(text1 = "Year: ", text2 = movie.year)
            TextDetailInfo(text1 = "Genre: ", text2 = movie.genre)
            TextDetailInfo(text1 = "Director: ", text2 = movie.director)
            TextDetailInfo(text1 = "Actors: ", text2 = movie.actors)
            TextDetailInfo(text1 = "Plot: ", text2 = movie.plot)
            TextDetailInfo(text1 = "Rating: ", text2 = movie.rating)
            FilmImagesRow()
        }

    }
}

@Composable
private fun FilmImagesRow() {
    LazyRow {
        items(movie.images) { movieImage ->
            AsyncImage(
                modifier = Modifier
                    .size(200.dp)
                    .padding(5.dp),
                contentScale = ContentScale.Crop,
                model = movieImage,
                contentDescription = "Movie Image"
            )
        }
    }
}

@Composable
private fun ColumnScope.FilmPoster() {
    Surface(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        AsyncMovieImage(
            imageLink = movie.poster,
            scale = 2f
        )
    }
}

@Composable
private fun DetailsBG(navController: NavController, content: @Composable () -> Unit = {}) {
    Scaffold(topBar = { DetailsTopBar(navController) }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            content()
        }
    }
}

@Composable
private fun DetailsTopBar(navController: NavController) {
    TopBarSimple(title = "Movie Details", navigationIcon = {
        IconButtonSimple(
            onClick = { navController.popBackStack() },
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Go Back"
        )
    })
}

@Composable
fun MovieBG() {
    AsyncBlurredImage(
        modifier = Modifier.fillMaxSize(),
        imageLink = movie.poster
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    DetailsScreen(
        navController = rememberNavController(),
        movieId = Movie.getMovies()[0].id
    )
}