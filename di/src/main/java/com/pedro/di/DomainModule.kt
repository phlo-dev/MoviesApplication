package com.pedro.di

import com.pedro.domain.models.ThreadContextProvider
import com.pedro.domain.usecases.MoviesFetchUseCase
import com.pedro.domain.usecases.SearchUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module.module

val domainModule = module {

    factory { (scope: CoroutineScope) -> MoviesFetchUseCase(scope, get()) }

    factory { (scope: CoroutineScope) -> SearchUseCase(scope, get()) }

    factory { ThreadContextProvider() }

}