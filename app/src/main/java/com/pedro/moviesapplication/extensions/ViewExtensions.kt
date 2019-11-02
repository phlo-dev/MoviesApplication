package com.pedro.moviesapplication.extensions

import androidx.appcompat.widget.SearchView

fun SearchView.setOnQueryTextListener(
    onQueryTextChanged: (String) -> Unit = {},
    onSubmit: (String) -> Unit
) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            onSubmit(query ?: "")
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            onQueryTextChanged(newText ?: "")
            return true
        }
    })
}