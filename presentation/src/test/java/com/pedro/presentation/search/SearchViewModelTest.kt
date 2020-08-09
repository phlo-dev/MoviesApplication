package com.pedro.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pedro.domain.models.ListPagination
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
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

    @Test
    fun `search WHEN call and has a search call with different query in loading MUST cancel the first and call the second`() {
        val firstQuery = "123"
        val secondQuery = "12434"
        stubSearchMoviesCall()
        stubSearchMoviesCancel()
        viewModel.search(firstQuery)
        stubSuccessInSearchMovies()
        viewModel.search(secondQuery)
        verify(exactly = 1) {
            searchMovies.execute(SearchMovies.Param(firstQuery, 1), any(), any())
        }
        verify(exactly = 1) { searchMovies.cancel() }
        verify(exactly = 1) {
            searchMovies.execute(SearchMovies.Param(secondQuery, 1), any(), any())
        }
    }

    @Test
    fun `search WHEN call and has a search call with same query in loading MUST maintain viewState to loading and not call the use case second time`() {
        stubSearchMoviesCall()
        viewModel.search()
        viewModel.search()
        verify(exactly = 1) { searchMovies.execute(any(), any(), any()) }
        assertEquals(ViewState.Status.LOADING, viewModel.getQueryViewState().value?.status)
    }

    @Test
    fun `hasMoreResults WHEN called after execute getMovies with success and total page answer is 1 UseCase MUST return false`() {
        stubSuccessInSearchMovies()
        viewModel.search()
        assertFalse(viewModel.hasMoreResults())
    }

    @Test
    fun `hasMoreResults WHEN call getMovies once success with total page 3 MUST return value true`() {
        stubSuccessInSearchMovies(ListPagination(listOf(), totalPage = 3))
        viewModel.search()
        assertTrue(viewModel.hasMoreResults())
    }

    @Test
    fun `hasMoreResults WHEN has not call once getMovies useCase with success MUST return true`() {
        assertTrue(viewModel.hasMoreResults())
    }

    private fun stubSearchMoviesCancel() {
        every { searchMovies.cancel() } returns Unit
    }

    private fun stubSearchMoviesCall() {
        every { searchMovies.execute(any(), any(), any()) } returns Unit
    }

    private fun stubSuccessInSearchMovies(data: ListPagination<Movie> = ListPagination(listOf())) {
        every {
            searchMovies.execute(any(), onSuccess = captureLambda(), onFailure = any())
        } answers {
            lambda<(ListPagination<Movie>) -> Unit>().invoke(data)
        }
    }

    private fun stubErrorInSearchMovies(exception: Exception) {
        every { searchMovies.execute(any(), any(), captureLambda()) } answers {
            lambda<(Throwable) -> Unit>().invoke(exception)
        }
    }
}