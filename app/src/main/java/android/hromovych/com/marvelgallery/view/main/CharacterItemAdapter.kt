package android.hromovych.com.marvelgallery.view.main

import android.hromovych.com.marvelgallery.R
import android.hromovych.com.marvelgallery.model.MarvelCharacter
import android.hromovych.com.marvelgallery.view.common.ItemAdapter
import android.hromovych.com.marvelgallery.view.common.bindView
import android.hromovych.com.marvelgallery.view.common.loadImage
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CharacterItemAdapter(
    val character: MarvelCharacter,
    val clicked: (MarvelCharacter) -> Unit
) : ItemAdapter<CharacterItemAdapter.ViewHolder>(R.layout.item_character) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textView by bindView<TextView>(R.id.textView)
        val imageView by bindView<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun ViewHolder.onBindViewHolder() {
        textView.text = character.name
        imageView.loadImage(character.imageUrl)
        itemView.setOnClickListener {clicked(character)}
    }
}