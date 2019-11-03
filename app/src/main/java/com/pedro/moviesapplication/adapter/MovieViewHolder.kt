package com.pedro.moviesapplication.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.extensions.loadImageUrl
import com.pedro.presentation.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Movie, onClick: (Movie) -> Unit) = itemView.run {
        itemNameFilmTextView.text = item.name
        itemFilmImageView.loadImageUrl(item.image, R.drawable.ic_movies_placeholder)
        setOnClickListener { onClick(item) }
    }

}