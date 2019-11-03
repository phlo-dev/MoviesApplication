package com.pedro.moviesapplication.features.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.features.FilmsFragmentDirections
import com.pedro.presentation.models.GenreTypeEnum
import com.pedro.presentation.models.Movie

class GenreMovieFragment : BaseFragment() {
    private var genreType: GenreTypeEnum = GenreTypeEnum.ACTION //default genre

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_genre_movie, container, false)

    fun onClickMovie(item: Movie) = navController.safeNavigate(
        FilmsFragmentDirections.actionFilmsFragmentToFilmDetailsFragment(item)
    )

    companion object {
        fun newInstance(enum: GenreTypeEnum) = GenreMovieFragment().also {
            it.genreType = enum
        }
    }
}