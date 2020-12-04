package android.hromovych.com.marvelgallery.data

import android.hromovych.com.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

interface MarvelRepository {
    fun getAllCharacters(searchQuery: String?): Single<List<MarvelCharacter>>

    companion object : Provider<MarvelRepository>() {
        override fun creator() = MarvelRepositoryImpl()
    }
}