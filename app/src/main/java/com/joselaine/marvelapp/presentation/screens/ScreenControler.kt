package com.joselaine.marvelapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.joselaine.marvelapp.presentation.viewmodels.CharactersViewModel

@Composable
fun ScreenController(
    modifier: Modifier = Modifier, navController: NavHostController
) {
    val viewModel = hiltViewModel<CharactersViewModel>()

    val charactersPagingData = viewModel.charactersPagingData("").collectAsLazyPagingItems()

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            Home(characterPagingData = charactersPagingData,
                onRetry = { charactersPagingData.retry() },
                clickOnCharacter = { navController.navigate("details") })
        }
        composable(Screens.Search.route) {
            Search()
        }
        composable(Screens.Details.route) {
            Details()
        }
    }
}
