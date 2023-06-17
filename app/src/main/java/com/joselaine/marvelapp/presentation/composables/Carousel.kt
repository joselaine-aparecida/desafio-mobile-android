package com.joselaine.marvelapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun Carousel(listOfImage: MutableList<String?>) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        content = {
            items(listOfImage) { image ->
                val imagePainter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                )

                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewCarousel() {
    Carousel(mutableListOf())
}
