package com.pedro.di

import com.pedro.presentation.genre.GenreMovieViewModel
import com.pedro.presentation.models.GenreTypeEnum
import com.pedro.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {

    viewModel { (genreType: GenreTypeEnum) -> GenreMovieViewModel(genreType) }

    viewModel { SearchViewModel() }

}