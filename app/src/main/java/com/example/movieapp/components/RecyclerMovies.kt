package com.example.movieapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.movieapp.models.Movie

@Composable
fun AsyncImageRecycler(list: List<Movie>, onItemClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
        items(list) { movie ->
            AsyncMovieRow(movie = movie, onItemClick = onItemClick)
        }
    }
}

@Composable
fun AsyncMovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    var showDetails by remember { mutableStateOf(false) }
    val cornerDp = 10.dp
    Card(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(cornerDp))
            .clickable { onItemClick(movie.id) },
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(7.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically),
                color = Color.Transparent
            ) {
                AsyncMovieImage(imageLink = movie.poster)
            }
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                TextRecyclerTitle(movie.title)
                Divider(thickness = 1.dp)
                TextRecyclerDetails(text = "Director: ${movie.director}")
                TextRecyclerDetails(text = "Genre: ${movie.genre}")
                TextRecyclerDetails(text = "Year: ${movie.year}")
                IconButtonSimple(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { showDetails = !showDetails },
                    color = Color.Unspecified,
                    imageVector = Icons.Outlined.KeyboardArrowDown
                )
                AnimatedVisibility(visible = showDetails) {
                    TextRecyclerDetails(text = movie.plot)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewRow() {
    Column {
        AsyncMovieRow(movie = Movie.getMovies()[0])
    }

}
