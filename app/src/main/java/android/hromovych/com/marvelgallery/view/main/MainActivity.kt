package android.hromovych.com.marvelgallery.view.main

import android.hromovych.com.marvelgallery.R
import android.hromovych.com.marvelgallery.data.MarvelRepository
import android.hromovych.com.marvelgallery.model.MarvelCharacter
import android.hromovych.com.marvelgallery.presenter.MainPresenter
import android.hromovych.com.marvelgallery.presenter.Presenter
import android.hromovych.com.marvelgallery.view.common.BaseActivityWithPresenter
import android.hromovych.com.marvelgallery.view.common.addOnTextChangedListener
import android.hromovych.com.marvelgallery.view.common.bindToSwipeRefresh
import android.hromovych.com.marvelgallery.view.common.toast
import android.hromovych.com.marvelgallery.view.main.main.MainView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainView {

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)

    override val presenter by lazy{ MainPresenter(this, MarvelRepository.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        swipeRefreshView.setOnRefreshListener {
            presenter.onRefresh()
        }
        searchView.addOnTextChangedListener {
            onTextChanged{text, _, _, _->
                presenter.onSearchChanged(text)
            }
        }
        presenter.onViewCreated()
    }

    override fun show(items: List<MarvelCharacter>) {
        val categoryItemAdapter = items.map(::CharacterItemAdapter)
        recyclerView.adapter = MainListAdapter(categoryItemAdapter)
    }

    override fun showError(error: Throwable) {
        toast("Error: ${error.message}")
        error.printStackTrace()
    }
}