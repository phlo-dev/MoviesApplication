package com.pedro.moviesapplication.extensions

import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.pedro.moviesapplication.R
import com.squareup.picasso.Picasso

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

fun ImageView.loadImageUrl(url: String?, @DrawableRes placeHolder: Int? = null) {
    Picasso.get()
        .load(url)
        .run {
            placeHolder?.let { id -> placeholder(id).error(id) } ?: this
        }
        .fit()
        .centerCrop()
        .into(this)
}