package com.pedro.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pedro.data_remote.BASE_URL
import com.pedro.data_remote.factory.RequestInterceptor
import com.pedro.data_remote.factory.ServiceClientFactory
import com.pedro.data_remote.service.MoviesWebService
import org.koin.dsl.module.module

val remoteWebServicesModule = module {
    single { ServiceClientFactory.createHttpLoggingInterceptor() }

    single { ServiceClientFactory.createOkHttpClient(get(), get()) }

    single { RequestInterceptor() }

    factory { CoroutineCallAdapterFactory()  }

    single { ServiceClientFactory.createClient<MoviesWebService>(BASE_URL, get(), get()) }
}