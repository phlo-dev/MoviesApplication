package com.pedro.presentation.genre

import androidx.lifecycle.*
import com.pedro.presentation.extensions.asLiveData
import com.pedro.presentation.extensions.createViewState
import com.pedro.presentation.models.GenreTypeEnum
import com.pedro.presentation.models.Movie
import kotlinx.coroutines.cancel

class GenreMovieViewModel : ViewModel(), LifecycleObserver {
    var genreType: GenreTypeEnum? = null
    private val genreMoviesViewState = createViewState<List<Movie>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchMovieListByGenre() {
        if(genreMoviesViewState.value?.isLoading() == true) return
        
    }

    fun getGenreMoviesViewState() = genreMoviesViewState.asLiveData()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}