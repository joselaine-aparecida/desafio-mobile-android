package com.joselaine.marvelapp.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int,MarvelCharacter>

    fun getCachedCharacters(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MarvelCharacter>>

}