package com.joselaine.marvelapp.data.repository

import androidx.paging.PagingSource
import com.joselaine.marvelapp.data.models.DataWrapperResponse
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.presentation.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>
) : CharactersRepository {
    override fun getCharacters(query: String): PagingSource<Int, MarvelCharacter> {
        return CharactersPagingSource(remoteDataSource, query)
    }
}