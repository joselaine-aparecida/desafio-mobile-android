package com.joselaine.marvelapp.data.repository

import androidx.paging.PagingSource
import com.joselaine.marvelapp.domain.models.MarvelCharacter

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int,MarvelCharacter>
}