package com.example.movieapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.movieapp.R

@Composable
fun BlurredImage(modifier: Modifier = Modifier, imageId: Int) {
    Image(
        modifier = modifier
            .alpha(0.8f)
            .blur(8.dp),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = imageId),
        contentDescription = "ImageBackGround"
    )
}

@Composable
fun BlurredImage(modifier: Modifier = Modifier, imageLink: String) {
    SubcomposeAsyncImage(
        modifier = modifier
            .alpha(0.8f)
            .blur(8.dp),
        model = imageLink,
        contentScale = ContentScale.Crop,
        contentDescription = "ImageBackGround"
    )
}

@Composable
fun RoundedCornerImage() {

}

@Preview
@Composable
fun ImagePreview() {
    Column {
        BlurredImage(imageId = R.drawable.kingsman)
        BlurredImage(imageLink = "https://ae01.alicdn.com/kf/S820600831a4341fca85bb4e6b26ac5eaU.jpg_640x640Q90.jpg_.webp")
    }

}