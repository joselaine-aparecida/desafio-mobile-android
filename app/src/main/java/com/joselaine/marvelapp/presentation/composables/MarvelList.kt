package com.joselaine.marvelapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.presentation.models.CharacterItem

private const val ItensInRow = 5
@Composable
fun MarvelList(
    characterPagingData: LazyPagingItems<MarvelCharacter>,
    onRetry: () -> Unit,
    clickOnCharacter: (Int) -> Unit
) {
    val firstFiveCharacters =
        characterPagingData.itemSnapshotList.items.take(ItensInRow)

    Column {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(firstFiveCharacters) { character ->
                Box(
                    modifier = Modifier
                        .width(400.dp)
                        .height(200.dp)
                ) {
                    MarvelCard(
                        characterItem = CharacterItem(
                            name = character.name,
                            imageUrl = character.imageUrl
                        ),
                    ) { clickOnCharacter(character.id.toInt()) }
                }
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                count = characterPagingData.itemCount,
                key = characterPagingData.itemKey(),
                contentType = characterPagingData.itemContentType()
            ) { index ->
                val character = characterPagingData[index]

                if (index >= ItensInRow) {
                    MarvelCard(
                        modifier = Modifier.width(400.dp),
                        characterItem = CharacterItem(
                            name = character?.name ?: "",
                            imageUrl = character?.imageUrl
                        )
                    ) {
                        character?.id?.let { idString -> clickOnCharacter(idString.toInt()) }
                    }
                }
            }

            when (characterPagingData.loadState.refresh) {
                is LoadState.Error -> { item { MarvelError(onRetry) } }
                is LoadState.Loading -> { item { MarvelLoading() } }
                else -> {}
            }

            when (characterPagingData.loadState.append) {
                is LoadState.Error -> { item { MarvelError(onRetry) } }
                is LoadState.Loading -> { item { MarvelLoading() } }
                else -> {}
            }
        }
    }
}