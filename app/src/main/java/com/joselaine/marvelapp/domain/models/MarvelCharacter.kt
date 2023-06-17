package com.joselaine.marvelapp.domain.models

import com.joselaine.marvelapp.data.models.CharacterResponse

data class MarvelCharacter(
    val name: String,
    val imageUrl: String,
)

fun CharacterResponse.toCharacterModel(): MarvelCharacter {
    return MarvelCharacter(
        name = this.name,
        imageUrl = "${this.thumbnail.path}.${this.thumbnail.extension}"
            .replace("http", "https")
    )
}
