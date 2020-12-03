package android.hromovych.com.marvelgallery.presenter

import android.hromovych.com.marvelgallery.data.MarvelRepository
import android.hromovych.com.marvelgallery.data.applySchedulers
import android.hromovych.com.marvelgallery.data.subscribeBy
import android.hromovych.com.marvelgallery.view.main.main.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(val view: MainView, val repository: MarvelRepository) : BasePresenter() {
    fun onViewCreated() {
        loadCharacters()
    }

    fun onRefresh() {
        loadCharacters()
    }

    private fun loadCharacters() {
        subscriptions.add(repository.getAllCharacters()
            .applySchedulers()
            .doOnSubscribe { view.refresh = true }
            .doFinally { view.refresh = false }
            .subscribeBy(
                onSuccess = view::show,
                onError = view::showError
            ))

    }
}