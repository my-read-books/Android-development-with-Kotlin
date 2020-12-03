package android.hromovych.com.marvelgallery.helpers

import android.hromovych.com.marvelgallery.model.MarvelCharacter
import android.hromovych.com.marvelgallery.view.main.main.MainView

class BaseMainView(
    var onShow: (items: List<MarvelCharacter>) -> Unit = {},
    val onShowError: (error: Throwable) -> Unit = {},
    override var refresh: Boolean = false
) : MainView {
    override fun show(items: List<MarvelCharacter>) {
        onShow(items)
    }
    override fun showError(error: Throwable) {
        onShowError(error)
    }
}