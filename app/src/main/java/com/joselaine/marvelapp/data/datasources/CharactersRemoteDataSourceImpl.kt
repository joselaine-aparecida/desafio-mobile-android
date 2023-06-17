package com.joselaine.marvelapp.data.datasources

import com.joselaine.marvelapp.data.MarvelApi
import com.joselaine.marvelapp.data.models.DataWrapperResponse
import com.joselaine.marvelapp.data.repository.CharactersRemoteDataSource
import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDataSource<DataWrapperResponse> {
    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
        return marvelApi.getCharacters(queries)
    }
}