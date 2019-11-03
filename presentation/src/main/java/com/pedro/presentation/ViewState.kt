package com.pedro.presentation

import com.pedro.presentation.ViewState.Status.*

data class ViewState<D>(val status: Status, val data: D? = null, val throwable: Throwable? = null) {
    companion object {

        fun <T> success(data: T? = null) =
            ViewState(status = SUCCESS, data = data)

        fun <T> error(throwable: Throwable) =
            ViewState<T>(status = ERROR, throwable = throwable)

        fun <T> loading() = ViewState<T>(status = LOADING)

        fun <T> initializing() = ViewState<T>(status = NEUTRAL)

    }

    fun isLoading() = status == LOADING

    enum class Status { SUCCESS, ERROR, LOADING, NEUTRAL }

}