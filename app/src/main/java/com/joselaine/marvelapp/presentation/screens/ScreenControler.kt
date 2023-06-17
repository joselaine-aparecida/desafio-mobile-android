package com.joselaine.marvelapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.joselaine.marvelapp.presentation.composables.CharacterItem
import com.joselaine.marvelapp.presentation.composables.OverlayCard
import com.joselaine.marvelapp.presentation.viewmodels.CharactersViewModel

@Composable
fun ScreenController(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            Home {
                navController.navigate("details")
            }
        }
        composable(Screens.AboutUs.route) {
            AboutUs()
        }
        composable(Screens.Details.route){
            Details()
        }
    }
}

@Composable
fun Details() {
    Text(text = "Tela de Detalhes")

}

@Composable
fun AboutUs() {
    Text(text = "Tela About")

}


@Composable
fun Home(clickOnCharacter: () -> Unit) {
    CharacterList(clickOnCharacter)

}

@Composable
fun CharacterList(clickOnCharacter: () -> Unit) {
    val viewModel = hiltViewModel<CharactersViewModel>()
    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val shouldStartPaginate = remember {
        derivedStateOf {
            (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    val list = viewModel.charactersPagingData("").collectAsLazyPagingItems()
    val context = LocalContext.current


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            count = list.itemCount,
            key = list.itemKey(),
            contentType = list.itemContentType()
        )
        { index ->
            val item = list[index]

            OverlayCard(characterItem = CharacterItem(item?.name ?: "", item?.imageUrl)) {
                clickOnCharacter()
            }

        }
    }

}
