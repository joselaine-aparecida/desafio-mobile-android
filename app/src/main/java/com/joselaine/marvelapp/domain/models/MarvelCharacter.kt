package com.joselaine.marvelapp.domain.models

import com.joselaine.marvelapp.data.models.CharacterResponse

data class MarvelCharacter(
    val name: String,
    val description: String,
    val imageUrl: String,
)

fun CharacterResponse.toCharacterModel(): MarvelCharacter {
    return MarvelCharacter(
        name = this.name,
        description = this.description,
        imageUrl = "${this.thumbnail.path}.${this.thumbnail.extension}"
            .replace("http", "https")
    )
}
