package com.joselaine.marvelapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
        composable(Screens.Search.route) {
            Search()
        }
        composable(Screens.Details.route) {
            Details()
        }
    }
}
