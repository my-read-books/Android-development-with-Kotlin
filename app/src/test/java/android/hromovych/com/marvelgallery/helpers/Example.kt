package android.hromovych.com.marvelgallery.helpers

import android.hromovych.com.marvelgallery.model.MarvelCharacter

object Example {
    val exampleCharacter = MarvelCharacter("ExampleName", "ExampleImageUrl")
    val exampleCharacterList = listOf(
        exampleCharacter,
        MarvelCharacter("Name1", "ImageUrl1"),
        MarvelCharacter("Name2", "ImageUrl2")
    )
}