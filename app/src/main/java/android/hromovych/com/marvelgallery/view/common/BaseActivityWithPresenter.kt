package android.hromovych.com.marvelgallery.view.common

import android.hromovych.com.marvelgallery.presenter.Presenter
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivityWithPresenter : AppCompatActivity() {
    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}