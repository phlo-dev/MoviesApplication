package com.pedro.moviesapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pedro.moviesapplication.R
import com.pedro.presentation.models.Film

class FilmAdapter(
    private val list: List<Film>,
    private val onClick: (Film) -> Unit
) : RecyclerView.Adapter<FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) =
        holder.bind(list[position], onClick)
}