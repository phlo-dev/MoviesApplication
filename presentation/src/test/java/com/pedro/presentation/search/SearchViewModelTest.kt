package com.pedro.presentation.search

import com.pedro.domain.usecases.SearchMovies
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin

class SearchViewModelTest {
    private lateinit var viewModel: SearchViewModel
    private val searchMovies: SearchMovies = mockk()
    private val testModule = module { single { searchMovies } }

    @Before
    fun setup() {
        startKoin(listOf(testModule))
        viewModel = SearchViewModel()
    }

    @After
    fun reset() {
        stopKoin()
    }

    @Test
    fun `search `(){

    }
}