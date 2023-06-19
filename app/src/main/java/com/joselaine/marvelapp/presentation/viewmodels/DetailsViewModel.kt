package com.joselaine.marvelapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {


    suspend fun getDetails(id: Int): MarvelCharacter {
        return getCharacterUseCase.invoke(id)
    }

}