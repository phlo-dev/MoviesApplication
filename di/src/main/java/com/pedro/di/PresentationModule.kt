package com.pedro.di

import com.pedro.presentation.genre.GenreMovieViewModel
import com.pedro.presentation.search.SearchViewModel
import org.koin.dsl.module.module

val presentationModule = module {

    factory { GenreMovieViewModel() }

    factory { SearchViewModel() }

}