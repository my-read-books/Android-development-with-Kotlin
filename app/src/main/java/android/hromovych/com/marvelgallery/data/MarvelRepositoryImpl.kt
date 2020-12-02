package android.hromovych.com.marvelgallery.data

import android.hromovych.com.marvelgallery.data.network.MarvelApi
import android.hromovych.com.marvelgallery.data.network.provider.retrofit
import android.hromovych.com.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class MarvelRepositoryImpl : MarvelRepository {
    val api = retrofit.create(MarvelApi::class.java)

    override fun getAllCharacters(): Single<List<MarvelCharacter>> = api.getCharacters(
        offset = 0,
        limit = elementsOnListLimit
    ).map {
        it.data?.results.orEmpty().map(::MarvelCharacter) // 1
    }
    companion object {
        const val elementsOnListLimit = 50
    }
}