package com.joselaine.marvelapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.presentation.composables.OverlayCard
import com.joselaine.marvelapp.presentation.models.CharacterItem
import com.joselaine.marvelapp.presentation.viewmodels.CharactersViewModel

@Composable
fun ScreenController(
    modifier: Modifier = Modifier, navController: NavHostController
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
        composable(Screens.Details.route) {
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

    val charactersPagingData: LazyPagingItems<MarvelCharacter> =
        viewModel.charactersPagingData("").collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            count = charactersPagingData.itemCount,
            key = charactersPagingData.itemKey(),
            contentType = charactersPagingData.itemContentType()
        ) { index ->
            val character = charactersPagingData[index]

            OverlayCard(
                characterItem = CharacterItem(
                    name = character?.name ?: "",
                    imageUrl = character?.imageUrl
                )
            ) {
                clickOnCharacter()
            }
        }
    }

}
