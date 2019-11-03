package com.pedro.moviesapplication.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


fun Fragment.setupToolbar(
    toolbarId: Int,
    titleString: String? = "",
    setUpNavigation: Boolean = false,
    hasOptionMenu: Boolean = true
) = (requireActivity() as AppCompatActivity).run {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.setDisplayHomeAsUpEnabled(setUpNavigation)
    supportActionBar?.title = if (titleString == "") null else titleString
    this@setupToolbar.setHasOptionsMenu(hasOptionMenu)
}

fun Fragment.setupToolbar(
    toolbarId: Int,
    titleId: Int,
    setUpNavigation: Boolean = false,
    hasOptionMenu: Boolean = true
) = setupToolbar(toolbarId, getString(titleId), setUpNavigation, hasOptionMenu)

fun Fragment.toast(message: Int) = requireContext().toast(message)

fun Fragment.toast(message: String) = requireContext().toast(message)

fun Fragment.getDrawable(@DrawableRes drawableId: Int) =
    ContextCompat.getDrawable(requireContext(), drawableId)

fun Fragment.getColor(@ColorRes colorId: Int) = ContextCompat.getColor(requireContext(), colorId)

fun Fragment.hideKeyboard() {
    val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = requireActivity().currentFocus
    if (view == null) {
        view = View(requireContext())
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}