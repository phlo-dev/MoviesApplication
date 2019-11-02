package com.pedro.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    fun search(query: String){

    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}