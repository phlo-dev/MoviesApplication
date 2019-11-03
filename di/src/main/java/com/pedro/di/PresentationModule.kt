package com.pedro.di

import com.pedro.presentation.genre.GenreViewModel
import com.pedro.presentation.search.SearchViewModel
import org.koin.dsl.module.module

val presentationModule = module {

    single { GenreViewModel() }

    single { SearchViewModel() }

}