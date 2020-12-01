package android.hromovych.com.marvelgallery.view.main

import android.hromovych.com.marvelgallery.R
import android.hromovych.com.marvelgallery.model.MarvelCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val characters = listOf(
        MarvelCharacter(
            name = "3-D Man",
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        ),
        MarvelCharacter(
            name = "Abomination (Emil Blonsky)",
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val categoryItemAdapters = characters.map(::CharacterItemAdapter)
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }
}