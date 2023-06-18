package com.joselaine.marvelapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.presentation.models.CharacterItem
import com.joselaine.marvelapp.presentation.viewmodels.CharactersViewModel

@Composable
fun CharacterList(clickOnCharacter: (MarvelCharacter?) -> Unit) {
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
                clickOnCharacter(character)
            }
        }
    }

}
