package com.pedro.presentation.search

import androidx.lifecycle.ViewModel
import com.pedro.domain.usecases.SearchMovies
import com.pedro.presentation.ViewState.Companion.loading
import com.pedro.presentation.extensions.*
import com.pedro.presentation.mapper.fromDomain
import com.pedro.presentation.models.Movie
import org.koin.standalone.KoinComponent

class SearchViewModel : ViewModel(), KoinComponent {
    private val queryViewState = createViewState<List<Movie>>()
    private val searchMovies: SearchMovies by useCase()
    private var searchQuery = ""

    fun search(query: String = searchQuery) {
        if (queryViewState.value?.isLoading() == true) searchMovies.cancel()
        searchQuery = query
        queryViewState.value = loading()
        searchMovies.execute(
            param = query,
            onSuccess = { queryViewState.postSuccess(it.fromDomain()) },
            onFailure = { queryViewState.postFailure(it) }
        )
    }

    fun hasMoreResults() = searchMovies.hasMoreResults()

    fun getQueryViewState() = queryViewState.asLiveData()

    override fun onCleared() {
        searchMovies.cancel()
        super.onCleared()
    }
}