package com.pedro.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedro.presentation.ViewState.Companion.loading
import com.pedro.presentation.extensions.asLiveData
import com.pedro.presentation.extensions.createViewState
import com.pedro.presentation.models.Movie
import kotlinx.coroutines.cancel

class SearchViewModel : ViewModel() {
    private val queryViewState = createViewState<List<Movie>>()
    private var searchQuery = ""

    fun search(query: String) {
        if (queryViewState.value?.isLoading() == true) return
        searchQuery = query
        queryViewState.value = loading()

    }

    fun getQueryViewState() = queryViewState.asLiveData()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}