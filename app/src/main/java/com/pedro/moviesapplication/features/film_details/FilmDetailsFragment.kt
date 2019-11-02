package com.pedro.moviesapplication.features.film_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.extensions.loadImageUrl
import com.pedro.moviesapplication.extensions.navArgs
import com.pedro.moviesapplication.extensions.setupToolbar
import kotlinx.android.synthetic.main.fragment_film_details.*

class FilmDetailsFragment : BaseFragment() {
    private val args by navArgs<FilmDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_film_details, container, false)

    override fun setupViews() = args.film.run {
        setupToolbar(R.id.filmDetailsToolbar, name, true, hasOptionMenu = false)
        filmDetailsImageView.loadImageUrl(image, R.drawable.ic_movies_placeholder)
        filmDescriptionDetailsTextView.text = description
    }
}