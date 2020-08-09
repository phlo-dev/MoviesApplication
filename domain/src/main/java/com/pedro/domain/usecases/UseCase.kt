package com.pedro.domain.usecases

import com.pedro.domain.models.Response
import com.pedro.domain.models.ThreadContextProvider
import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

abstract class UseCase<P, R>(private val scope: CoroutineScope) : KoinComponent {
    private val threadContextProvider: ThreadContextProvider by inject()

    abstract suspend fun getResult(param: P): Response<R>

    fun execute(
        param: P,
        onSuccess: (R) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        scope.launch(threadContextProvider.io) {
            val result = getResult(param)
            withContext(threadContextProvider.main) {
                when (result) {
                    is Response.Success -> onSuccess(result.data)
                    is Response.Failure -> onFailure(result.throwable)
                }
            }
        }
    }

    fun cancel() = scope.coroutineContext.cancelChildren()
}