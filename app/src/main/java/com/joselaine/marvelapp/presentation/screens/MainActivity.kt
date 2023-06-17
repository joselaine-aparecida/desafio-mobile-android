package com.joselaine.marvelapp.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joselaine.marvelapp.R
import com.joselaine.marvelapp.presentation.composables.CardItem
import com.joselaine.marvelapp.presentation.composables.CharacterItem
import com.joselaine.marvelapp.presentation.ui.theme.MarvelAppTheme
import com.joselaine.marvelapp.presentation.ui.theme.RedMarvel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
               NavigationController()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    Scaffold(
        bottomBar = { BottomAppBar() },
    ) {
        it
        CardItem(CharacterItem("meuteste", "", true), modifier = Modifier)
    }
}


@Composable
fun BottomAppBar() {
    BottomNavigation(
        backgroundColor = RedMarvel,
        contentColor = Color.White,
        modifier = Modifier
            .height(70.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        elevation = 16.dp
    ) {
        BottomNavigationItem(
            selected = false,
            onClick = { /* Ação do item Heróis */ },
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "", tint = Color.White) },
            label = { Text(text = stringResource(R.string.app_name)) },
            alwaysShowLabel = false,
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White
        )

        BottomNavigationItem(
            selected = false,
            onClick = { /* Ação do item Favoritos */ },
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "", tint = Color.White) },
            label = { Text(text = stringResource(R.string.app_name)) },
            alwaysShowLabel = false,
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White
        )

        BottomNavigationItem(
            selected = false,
            onClick = { /* Ação do item Buscar */ },
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "", tint = Color.White) },
            label = { Text(text = stringResource(R.string.app_name)) },
            alwaysShowLabel = true,
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarvelAppTheme {
        MyScreen()
    }
}

