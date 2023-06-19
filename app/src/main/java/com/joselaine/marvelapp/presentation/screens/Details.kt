package com.joselaine.marvelapp.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.presentation.composables.MarvelLoading
import com.joselaine.marvelapp.presentation.viewmodels.DetailsViewModel

@Composable
fun Details(navController: NavController) {
    val id = remember { mutableStateOf(0) }
    val arguments = navController.currentBackStackEntry?.arguments
    id.value = arguments?.getInt("id") ?: 0

    val viewModel = hiltViewModel<DetailsViewModel>()

    var result by remember { mutableStateOf<MarvelCharacter?>(null) }

    LaunchedEffect(Unit) {
        val fetchedData = viewModel.getDetails(id.value)
        result = fetchedData
    }

    AnimatedVisibility(visible = result == null) {
        MarvelLoading()
    }

    AnimatedVisibility(visible = result != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val imagePainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(result?.imageUrl)
                    .crossfade(true)
                    .build(),
            )

            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = result?.name ?: "",
                style = MaterialTheme.typography.h5,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = result?.description ?: "",
                style = MaterialTheme.typography.body1,
                color = Color.Gray,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}