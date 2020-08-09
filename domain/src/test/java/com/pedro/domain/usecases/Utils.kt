package com.pedro.domain.usecases

import com.pedro.domain.models.ThreadContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module.module

@ExperimentalCoroutinesApi
val testModule = module {
    single {
        object : ThreadContextProvider() {
            override val main = Dispatchers.Unconfined
            override val io = Dispatchers.Unconfined
        } as ThreadContextProvider
    }
}