package com.pedro.presentation.genre

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.pedro.domain.usecases.MoviesFetchUseCase
import com.pedro.presentation.ViewState.Companion.loading
import com.pedro.presentation.extensions.*
import com.pedro.presentation.mapper.fromDomain
import com.pedro.presentation.mapper.toParam
import com.pedro.presentation.models.GenreTypeEnum
import com.pedro.presentation.models.Movie
import org.koin.standalone.KoinComponent

class GenreMovieViewModel : ViewModel(), LifecycleObserver, KoinComponent {
    private val genreMoviesViewState = createViewState<List<Movie>>()
    private val moviesFetchUseCase: MoviesFetchUseCase by useCase()

    var genreType: GenreTypeEnum = GenreTypeEnum.getGenre()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchMovieListByGenre() {
        if (genreMoviesViewState.value?.isLoading() == true) return
        genreMoviesViewState.value = loading()

        moviesFetchUseCase.execute(
            param = genreType.toParam(),
            onSuccess = { genreMoviesViewState.postSuccess(it.fromDomain()) },
            onFailure = { genreMoviesViewState.postFailure(it) }
        )
    }

    fun refreshMovieList() {
        if (genreMoviesViewState.value?.isLoading() == true) moviesFetchUseCase.cancel()
        genreMoviesViewState.value = loading()

        moviesFetchUseCase.refresh(
            param = genreType.toParam(),
            onSuccess = { genreMoviesViewState.postSuccess(it.fromDomain()) },
            onFailure = { genreMoviesViewState.postFailure(it) }
        )
    }

    fun hasMoreResults() = moviesFetchUseCase.hasMoreResults()

    fun getGenreMoviesViewState() = genreMoviesViewState.asLiveData()

    override fun onCleared() {
        moviesFetchUseCase.cancel()
        super.onCleared()
    }
}