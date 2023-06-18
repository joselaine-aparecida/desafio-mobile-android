package com.joselaine.marvelapp.presentation.screens

import androidx.compose.runtime.Composable
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.presentation.composables.CharacterList

@Composable
fun Home(clickOnCharacter: (marvelCharacter: MarvelCharacter?) -> Unit) {
    CharacterList(clickOnCharacter)
}