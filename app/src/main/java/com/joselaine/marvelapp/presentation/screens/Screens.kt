package com.joselaine.marvelapp.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, var label: String, val icon: ImageVector) {
    object Home : Screens(ROUTE_HOME, "Home", Icons.Default.Home)
    object Search : Screens(ROUTE_SEARCH, "Search", Icons.Default.Search)
    object Details : Screens(ROUTE_DETAILS, "", Icons.Default.Person)
}

const val ROUTE_HOME = "home"
const val ROUTE_DETAILS = "details"
const val ROUTE_SEARCH = "search"
