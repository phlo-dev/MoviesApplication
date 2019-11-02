package com.pedro.moviesapplication.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.extensions.loadImageUrl
import com.pedro.presentation.models.Film
import kotlinx.android.synthetic.main.item_film.view.*

class FilmViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Film, onClick: (Film) -> Unit) = itemView.run {
        itemNameFilmTextView.text = item.name
        itemFilmImageView.loadImageUrl(item.image, R.drawable.ic_movies_placeholder)
        setOnClickListener { onClick(item) }
    }

}