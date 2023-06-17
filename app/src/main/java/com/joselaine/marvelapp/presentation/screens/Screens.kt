package com.joselaine.marvelapp.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, var label: String, val icon: ImageVector) {
    object Home : Screens(ROUTE_HOME, "Home", Icons.Default.Home)
    object AboutUs : Screens(ROUTE_ABOUT_US, "Sobre", Icons.Default.Person)
    object Details : Screens(ROUTE_DETAILS, "Sobre", Icons.Default.Person)
}

const val ROUTE_HOME = "home"
const val ROUTE_DETAILS = "details"
const val ROUTE_ABOUT_US = "about_us"
