package com.pedro.moviesapplication.extensions

import android.view.View
import android.view.animation.DecelerateInterpolator
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

fun View.animateWithScale(scaleX: Float = 1f, scaleY: Float = 1f) = animate()
    .scaleX(scaleX).scaleY(scaleY).setInterpolator(DecelerateInterpolator()).start()