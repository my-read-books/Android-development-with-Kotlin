package android.hromovych.com.marvelgallery.model

import android.hromovych.com.marvelgallery.data.network.dto.CharacterMarvelDto

data class MarvelCharacter(
    val name: String,
    val imageUrl: String
) {
    constructor(dto: CharacterMarvelDto) : this(
        name = dto.name,
        imageUrl = dto.imageUrl
    )
}
