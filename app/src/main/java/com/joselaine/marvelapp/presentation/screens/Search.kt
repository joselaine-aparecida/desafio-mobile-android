package com.joselaine.marvelapp.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.presentation.composables.MarvelCard
import com.joselaine.marvelapp.presentation.models.CharacterItem
import com.joselaine.marvelapp.presentation.ui.theme.RedMarvel
import com.joselaine.marvelapp.presentation.viewmodels.CharactersViewModel

@Composable
fun Search() {
    val viewModel = hiltViewModel<CharactersViewModel>()
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val charactersPagingData: LazyPagingItems<MarvelCharacter> =
        viewModel.charactersPagingData(textState.value.text).collectAsLazyPagingItems()
    Column {
        SearchView(Modifier, textState)
        AnimatedVisibility(visible = textState.value.text.isNotEmpty()) {
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

                    MarvelCard(
                        characterItem = CharacterItem(
                            name = character?.name ?: "", imageUrl = character?.imageUrl
                        )
                    ) {

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    modifier: Modifier = Modifier, state: MutableState<TextFieldValue>
) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle.Default.copy(color = Color.Black),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                tint = RedMarvel,
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(onClick = {
                    state.value =
                        TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                }) {
                    Icon(
                        Icons.Default.Close,
                        tint = RedMarvel,
                        contentDescription = "Apagar Texto de Busca",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape
    )
}