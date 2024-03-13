package com.example.movieapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun AsyncBlurredImage(modifier: Modifier = Modifier, imageLink: String) {
    SubcomposeAsyncImage(
        modifier = modifier
            .alpha(0.5f)
            .blur(8.dp),
        model = imageLink,
        contentScale = ContentScale.Crop,
        contentDescription = "ImageBackGround"
    )
}

@Composable
fun AsyncMovieImage(
    modifier: Modifier = Modifier,
    imageLink: String,
    contentDescription: String = "MoviePoster",
    scale: Float = 1f
) {
    val width = (scale * 72.8).dp
    val height = (scale * 112).dp
    SubcomposeAsyncImage(
        alignment = Alignment.Center,
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        model = imageLink,
        contentDescription = contentDescription
    )
}

@Preview
@Composable
fun ImagePreview() {
    Column {
        AsyncBlurredImage(imageLink = "https://ae01.alicdn.com/kf/S820600831a4341fca85bb4e6b26ac5eaU.jpg_640x640Q90.jpg_.webp")
    }

}