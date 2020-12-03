package android.hromovych.com.marvelgallery.helpers

import android.hromovych.com.marvelgallery.data.MarvelRepository
import android.hromovych.com.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class BaseMarvelRepository(
    val onGetCharacters: () -> Single<List<MarvelCharacter>>
) : MarvelRepository {
    override fun getAllCharacters() = onGetCharacters()
}