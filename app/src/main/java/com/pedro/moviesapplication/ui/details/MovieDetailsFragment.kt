package com.pedro.moviesapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.extensions.loadImageUrl
import com.pedro.moviesapplication.extensions.setupToolbar
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : BaseFragment() {
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_details, container, false)

    override fun setupViews() = args.movie.run {
        setupToolbar(R.id.filmDetailsToolbar, name, true, hasOptionMenu = false)
        filmDetailsImageView.loadImageUrl(image, R.drawable.ic_movies_placeholder)
        filmDescriptionDetailsTextView.text = description
    }
}