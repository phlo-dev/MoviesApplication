package com.pedro.presentation.genre

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.pedro.domain.usecases.GetMovies
import com.pedro.presentation.ViewState.Companion.loading
import com.pedro.presentation.extensions.*
import com.pedro.presentation.mapper.fromDomain
import com.pedro.presentation.mapper.toParam
import com.pedro.presentation.models.GenreTypeEnum
import com.pedro.presentation.models.Movie
import org.koin.standalone.KoinComponent

class GenreMovieViewModel(
    private val genreType: GenreTypeEnum
) : ViewModel(), LifecycleObserver, KoinComponent {
    private val genreMoviesViewState = createViewState<List<Movie>>()
    private val getMovies: GetMovies by useCase()
    private val movieList = mutableListOf<Movie>()
    private var currentPage = 1
    private var totalPage = 1

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchMovieListByGenre() {
        if (genreMoviesViewState.value?.isLoading() == true) return
        genreMoviesViewState.value = loading()

        getMovies.execute(
            param = GetMovies.Param(genreType.toParam(), currentPage),
            onSuccess = {
                movieList.addAll(it.list.fromDomain())
                currentPage++
                totalPage = it.totalPage
                genreMoviesViewState.postSuccess(movieList)
            },
            onFailure = { genreMoviesViewState.postFailure(it) }
        )
    }

    fun refreshMovieList() {
        if (genreMoviesViewState.value?.isLoading() == true) {
            getMovies.cancel()
            genreMoviesViewState.value = null
        }

        movieList.clear()
        currentPage = 1
        fetchMovieListByGenre()
    }

    fun hasMoreResults() = currentPage <= totalPage

    fun getGenreMoviesViewState() = genreMoviesViewState.asLiveData()

}