package com.pedro.di

import com.pedro.data.remote.MovieRemoteRepository
import com.pedro.data_remote.repository.MovieRemoteRepositoryImpl
import org.koin.dsl.module.module

val dataRemoteModule = module {

    factory { MovieRemoteRepositoryImpl(moviesWebService = get()) as MovieRemoteRepository }

}