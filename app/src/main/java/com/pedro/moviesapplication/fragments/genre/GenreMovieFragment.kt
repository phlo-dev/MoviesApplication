package com.pedro.moviesapplication.fragments.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.adapter.MovieAdapter
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.extensions.addScrollListener
import com.pedro.moviesapplication.fragments.MoviesFragmentDirections
import com.pedro.presentation.genre.GenreMovieViewModel
import com.pedro.presentation.models.GenreTypeEnum
import com.pedro.presentation.models.Movie
import kotlinx.android.synthetic.main.fragment_genre_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GenreMovieFragment : BaseFragment() {
    private var genreType: GenreTypeEnum = GenreTypeEnum.ACTION
    private val viewModel by viewModel<GenreMovieViewModel> { parametersOf(genreType) }
    private val movieAdapter by lazy { MovieAdapter { onClickMovie(it) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_genre_movie, container, false)

    private fun onClickMovie(item: Movie) = navController.safeNavigate(
        MoviesFragmentDirections.actionFilmsFragmentToFilmDetailsFragment(item)
    )

    override fun setupViews() {
        genreMoviesRecyclerView.adapter = movieAdapter
        genreMoviesRecyclerView.addScrollListener { lastVisibleItem ->
            if (lastVisibleItem == movieAdapter.itemCount && viewModel.hasMoreResults()) {
                viewModel.fetchMovieListByGenre()
            }
        }
        genreMovieRefreshLayout.setOnRefreshListener {
            genreMoviesProgressBar.isVisible = false
            viewModel.refreshMovieList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycle.removeObserver(viewModel)
    }

    override fun creatingObservers() {
        viewModel.getGenreMoviesViewState().handleWithFlow(
            onLoading = {
                if (!genreMovieRefreshLayout.isRefreshing) genreMoviesProgressBar.isVisible = true
            },
            onComplete = {
                genreMoviesProgressBar.isVisible = false
                genreMovieRefreshLayout.isRefreshing = false
            },
            onSuccess = { movieAdapter.list = it }
        )
    }

    companion object {
        fun newInstance(enum: GenreTypeEnum) = GenreMovieFragment().also {
            it.genreType = enum
        }
    }
}