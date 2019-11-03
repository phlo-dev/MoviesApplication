package com.pedro.di

import com.pedro.data.repository.MovieRepositoryImpl
import com.pedro.domain.repository.MoviesRepository
import org.koin.dsl.module.module

val dataModule = module {
    factory { MovieRepositoryImpl(remoteRepository = get()) as MoviesRepository }
}