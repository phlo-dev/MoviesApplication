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
    private val movieList = mutableListOf<Movie>()
    private var searchQuery = ""
    private var currentPage = 1
    private var totalPage = 1

    fun search(query: String = searchQuery) {
        when {
            queryViewState.value?.isLoading() == true && searchQuery == query -> return
            queryViewState.value?.isLoading() == true -> searchMovies.cancel()
        }
        refreshListIfNeeded(query)
        queryViewState.value = loading()
        searchMovies.execute(
            param = SearchMovies.Param(query = query, page = currentPage),
            onSuccess = {
                movieList.addAll(it.list.fromDomain())
                currentPage++
                totalPage = it.totalPage
                queryViewState.postSuccess(movieList)
            },
            onFailure = { queryViewState.postFailure(it) }
        )
    }

    private fun refreshListIfNeeded(query: String) {
        if (searchQuery == query) return
        searchQuery = query
        currentPage = 1
        totalPage = 1
        movieList.clear()
    }

    fun hasMoreResults() = currentPage <= totalPage

    fun getQueryViewState() = queryViewState.asLiveData()

}