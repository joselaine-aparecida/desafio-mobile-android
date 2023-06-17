package com.joselaine.marvelapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.joselaine.marvelapp.R
import com.joselaine.marvelapp.presentation.ui.theme.RedMarvel

@Composable
fun NavigationController() {
    val navController = rememberNavController()
    val systemController = rememberSystemUiController()
    systemController.setSystemBarsColor(RedMarvel)
    Scaffold(
        backgroundColor = Color.White,
        bottomBar = { BottomNavigation(navController) },
        topBar = { MarvelTopBar() }) {
        ScreenController(Modifier.padding(it), navController)
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val itemsInBottomNavigation = listOf(
        Screens.Home, Screens.AboutUs
    )
    BottomNavigation(
        backgroundColor = RedMarvel,
        modifier = Modifier
            .height(70.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        elevation = 16.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        itemsInBottomNavigation.forEach {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.label,
                        tint = if (currentRoute == it.route) Color.White
                        else Color.LightGray,
                        modifier = Modifier.size(30.dp)
                    )
                },
                selected = currentRoute == it.route,
                label = {
                    Text(
                        text = it.label, color = if (currentRoute == it.route) Color.White
                        else Color.LightGray, textAlign = TextAlign.Center, fontSize = 12.sp
                    )
                },
                onClick = {
                    if (currentRoute != it.route) {
                        navController.graph.startDestinationRoute?.let { item ->
                            navController.popBackStack(
                                item, false
                            )
                        }
                    }
                    if (currentRoute != it.route) {
                        navController.navigate(it.route) {
                            launchSingleTop = true
                        }
                    }
                },
                alwaysShowLabel = false,
                selectedContentColor = Color.White,
            )
        }
    }
}

@Composable
fun MarvelTopBar() {
    TopAppBar(
        modifier = Modifier.height(56.dp),
        backgroundColor = RedMarvel,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.marvel_logo),
                contentDescription = "Marvel Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(120.dp)
            )
        }
    }
}
