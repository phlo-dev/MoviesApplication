package com.pedro.moviesapplication.extensions

import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
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

fun RecyclerView.addScrollListener(
    function: (Int) -> Unit
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val lastVisibleItem = layoutManager.findLastVisibleItemPosition() + 1
            function(lastVisibleItem)
        }
    })
}

fun TabLayout.addOnTabSelectedListener(
    onTabReselected: (TabLayout.Tab) -> Unit = {},
    onTabUnselected: (TabLayout.Tab) -> Unit = {},
    onTabSelected: (TabLayout.Tab) -> Unit = {}
) {
    addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
        override fun onTabReselected(tab: TabLayout.Tab) {
            onTabReselected(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            onTabUnselected(tab)
        }

        override fun onTabSelected(tab: TabLayout.Tab) {
            onTabSelected(tab)
        }
    })
}

fun TabLayout.setOnlyTextSelectedAsBold() {
    addOnTabSelectedListener(
        onTabSelected = { tab ->
            getTextView(tab.position)?.run{
                textSize = 16f
                typeface = context.getFont(R.font.poppins_bold)
            }
        },
        onTabUnselected = { tab ->
            getTextView(tab.position)?.run{
                textSize = 16f
                typeface = context.getFont(R.font.poppins)
            }
        }
    )
}

fun TabLayout.getTextView(position: Int): TextView? =
    (((getChildAt(0) as? ViewGroup)?.getChildAt(position) as? ViewGroup)
        ?.getChildAt(1) as? TextView)