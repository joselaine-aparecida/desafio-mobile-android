package com.joselaine.marvelapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.presentation.composables.MarvelList

@Composable
fun Home(characterPagingData: LazyPagingItems<MarvelCharacter>, onRetry: () -> Unit, clickOnCharacter: (marvelCharacter: MarvelCharacter?) -> Unit) {
    MarvelList(characterPagingData, onRetry, clickOnCharacter)
}