package com.pedro.di

import com.pedro.domain.usecases.MoviesFetchUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module.module

val domainModule = module {
    factory { (scope: CoroutineScope) ->
        MoviesFetchUseCase(
            scope = scope,
            movieRepository = get()
        )
    }
}