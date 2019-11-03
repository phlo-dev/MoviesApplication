package com.pedro.presentation.search

import androidx.lifecycle.ViewModel
import com.pedro.domain.usecases.SearchUseCase
import com.pedro.presentation.ViewState.Companion.loading
import com.pedro.presentation.extensions.*
import com.pedro.presentation.mapper.fromDomain
import com.pedro.presentation.models.Movie
import org.koin.standalone.KoinComponent

class SearchViewModel : ViewModel(), KoinComponent {
    private val queryViewState = createViewState<List<Movie>>()
    private val searchUseCase: SearchUseCase by useCase()
    private var searchQuery = ""

    fun search(query: String) {
        if (queryViewState.value?.isLoading() == true) searchUseCase.cancel()
        searchQuery = query
        queryViewState.value = loading()
        searchUseCase.execute(
            param = query,
            onSuccess = { queryViewState.postSuccess(it.fromDomain()) },
            onFailure = { queryViewState.postFailure(it) }
        )
    }

    fun hasMoreResults() = searchUseCase.hasMoreResults()

    fun getQueryViewState() = queryViewState.asLiveData()

    override fun onCleared() {
        super.onCleared()
        searchUseCase.cancel()
    }
}