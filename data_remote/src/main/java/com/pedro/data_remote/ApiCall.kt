package com.pedro.data_remote

import com.pedro.domain.models.Response
import java.lang.Exception

inline fun <reified T> apiCall(
    call: () -> T
): Response<T> {
    return try {
        val result = call()
        Response.Success(result)
    } catch (exception: Exception) {
        Response.Failure(exception)
    }
}