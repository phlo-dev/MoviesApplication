package com.pedro.moviesapplication.features.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.adapter.MovieAdapter
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.features.FilmsFragmentDirections
import com.pedro.presentation.genre.GenreMovieViewModel
import com.pedro.presentation.models.GenreTypeEnum
import com.pedro.presentation.models.Movie
import kotlinx.android.synthetic.main.fragment_genre_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreMovieFragment : BaseFragment() {
    private var genreType: GenreTypeEnum = GenreTypeEnum.ACTION //default genre
    private val viewModel by viewModel<GenreMovieViewModel>()
    private val movieAdapter by lazy { MovieAdapter { onClickMovie(it) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_genre_movie, container, false)

    private fun onClickMovie(item: Movie) = navController.safeNavigate(
        FilmsFragmentDirections.actionFilmsFragmentToFilmDetailsFragment(item)
    )

    override fun setupViews() {
        genreMoviesRecyclerView.adapter = movieAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.genreType = genreType
        lifecycle.addObserver(viewModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycle.removeObserver(viewModel)
    }

    override fun creatingObservers() {
        viewModel.getGenreMoviesViewState().handleWithFlow(
            onLoading = {},
            onComplete = {},
            onSuccess = {}
        )
    }

    companion object {
        fun newInstance(enum: GenreTypeEnum) = GenreMovieFragment().also {
            it.genreType = enum
        }
    }
}