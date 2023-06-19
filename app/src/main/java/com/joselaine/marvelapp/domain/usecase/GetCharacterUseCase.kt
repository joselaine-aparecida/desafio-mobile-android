package com.joselaine.marvelapp.domain.usecase

import com.joselaine.marvelapp.data.repository.CharactersRepository
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import javax.inject.Inject

interface GetCharacterUseCase {
    suspend operator fun invoke(id: Int): MarvelCharacter
}

class GetCharacterUseCaseImpl @Inject constructor(
    private val characterRepository: CharactersRepository
) : GetCharacterUseCase {
    override suspend operator fun invoke(id: Int): MarvelCharacter {
        return characterRepository.getCharacter(id).characters[0]
    }
}