package com.pedro.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pedro.domain.models.Movie
import com.pedro.domain.usecases.SearchMovies
import com.pedro.presentation.ViewState
import com.pedro.presentation.ViewState.Status.ERROR
import com.pedro.presentation.ViewState.Status.SUCCESS
import io.mockk.every
import io.mockk.invoke
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import kotlin.test.assertEquals

class SearchViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
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
    fun `search WHEN has a error on search movies use case MUST post that error`() {
        val exception = Exception()
        stubErrorInSearchMovies(exception)
        viewModel.search()
        verify(exactly = 1) { searchMovies.execute(any(), any(), any()) }
        assertEquals(
            ViewState(ERROR, throwable = exception), viewModel.getQueryViewState().value
        )
    }

    @Test
    fun `search WHEN has a success in searchMovies use case MUST post a list of movies as success`() {
        stubSuccessInSearchMovies()
        viewModel.search()
        verify(exactly = 1) { searchMovies.execute(any(), any(), any()) }
        assertEquals(
            ViewState(status = SUCCESS, data = listOf()),
            viewModel.getQueryViewState().value
        )
    }

    private fun stubSuccessInSearchMovies() {
        every {
            searchMovies.execute(any(), onSuccess = captureLambda(), onFailure = any())
        } answers {
            lambda<(List<Movie>) -> Unit>().invoke(listOf())
        }
    }

    private fun stubErrorInSearchMovies(exception: Exception) {
        every { searchMovies.execute(any(), any(), captureLambda()) } answers {
            lambda<(Throwable) -> Unit>().invoke(exception)
        }
    }
}