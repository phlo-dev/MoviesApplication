@file:Suppress("unused")

package com.pedro.moviesapplication.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.pedro.moviesapplication.extensions.safeNavigateUp
import com.pedro.moviesapplication.extensions.toast
import com.pedro.presentation.ViewState
import com.pedro.presentation.extensions.handleWithFlow

open class BaseFragment : Fragment() {
    val navController by lazy { findNavController(this) }
    private var toastError: Toast? = null

    open fun handleErrors(throwable: Throwable) {
        toastError?.cancel()
        toastError = requireContext().toast(throwable.message ?: "")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navController.safeNavigateUp()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        creatingObservers()
    }

    open fun setupViews() {}

    open fun creatingObservers() {}

    fun <T> LiveData<ViewState<T>>.handleWithFlow(
        onLoading: () -> Unit = {},
        onNeutral: () -> Unit = {},
        onFailure: (Throwable) -> Unit = {},
        onComplete: (() -> Unit) = {},
        onSuccess: (T) -> Unit
    ) = handleWithFlow(
        this@BaseFragment,
        onNeutral,
        onLoading,
        onFailure,
        onComplete,
        onSuccess
    )


}