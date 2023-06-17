package com.joselaine.marvelapp.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joselaine.marvelapp.data.models.DataWrapperResponse
import com.joselaine.marvelapp.data.repository.CharactersRemoteDataSource
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.models.toCharacterModel

class CharactersPagingSource(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>,
    private val query: String
) : PagingSource<Int, MarvelCharacter>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        return try {
            val offset = params.key ?: 0
            val queries = hashMapOf(
                "offset" to offset.toString()
            )
            if (query.isNotEmpty()) {
                queries["nameStartsWith"] = query
            }
            val response = remoteDataSource.fetchCharacters(queries)
            val responseOffset = response.data.offset
            val totalCharacters = response.data.total
            LoadResult.Page(
                data = response.data.results.map { it.toCharacterModel() },
                prevKey = null,
                nextKey = if (responseOffset < totalCharacters) {
                    responseOffset + LIMIT
                } else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
    }

}