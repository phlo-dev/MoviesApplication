package com.pedro.di

import com.pedro.data_remote.factory.RequestInterceptor
import com.pedro.data_remote.factory.ServiceClientFactory
import org.koin.dsl.module.module

val remoteWebServicesModule = module {
    single { ServiceClientFactory.createHttpLoggingInterceptor() }

    single { ServiceClientFactory.createOkHttpClient(get(), get(), get(), get()) }

    single { RequestInterceptor() }
}