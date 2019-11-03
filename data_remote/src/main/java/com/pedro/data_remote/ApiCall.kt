package com.pedro.data_remote

import com.pedro.domain.models.Response

suspend fun <T> apiCall(call: suspend () -> T) = try {
    Response.Success(call())
} catch (t: Throwable) {
    Response.Failure(t)
}