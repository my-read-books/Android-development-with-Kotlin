package android.hromovych.com.marvelgallery.view.main.main

import android.hromovych.com.marvelgallery.model.MarvelCharacter

interface MainView {
    var refresh: Boolean
    fun show(items: List<MarvelCharacter>)
    fun showError(error: Throwable)
}