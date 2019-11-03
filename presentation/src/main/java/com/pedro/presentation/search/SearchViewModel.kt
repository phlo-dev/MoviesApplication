package com.pedro.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedro.presentation.ViewState
import com.pedro.presentation.ViewState.Companion.loading
import com.pedro.presentation.extensions.asLiveData
import com.pedro.presentation.models.Movie
import kotlinx.coroutines.cancel

class SearchViewModel : ViewModel() {
    private var searchQuery = ""
    private val queryViewState = MutableLiveData<ViewState<Movie>>()

    fun search(query: String){
        if(queryViewState.value?.isLoading() == true) return
        queryViewState.value = loading()
        
    }

    fun getQueryViewState() = queryViewState.asLiveData()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}