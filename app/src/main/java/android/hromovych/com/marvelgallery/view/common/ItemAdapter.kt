package android.hromovych.com.marvelgallery.view.common

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class ItemAdapter<T : RecyclerView.ViewHolder>
    (@LayoutRes open val layoutId: Int) {

    abstract fun onCreateViewHolder(itemView: View): T

    @Suppress("UNCHECKED_CAST")
    fun bindViewHolder(holder: RecyclerView.ViewHolder) {
        (holder as T).onBindViewHolder()
    }

    abstract fun T.onBindViewHolder()
}