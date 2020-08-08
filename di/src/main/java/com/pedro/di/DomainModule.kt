package com.pedro.di

import com.pedro.domain.models.ThreadContextProvider
import com.pedro.domain.usecases.GetMovies
import com.pedro.domain.usecases.SearchMovies
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module.module

val domainModule = module {

    factory { (scope: CoroutineScope) -> GetMovies(scope, get()) }

    factory { (scope: CoroutineScope) -> SearchMovies(scope, get()) }

    factory { ThreadContextProvider() }

}