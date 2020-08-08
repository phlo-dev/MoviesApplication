package com.pedro.presentation.genre

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pedro.domain.models.GenreTypeParamEnum
import com.pedro.domain.models.ListPagination
import com.pedro.domain.models.Movie
import com.pedro.domain.usecases.GetMovies
import com.pedro.presentation.ViewState
import com.pedro.presentation.ViewState.Status.*
import com.pedro.presentation.mapper.fromDomain
import com.pedro.presentation.models.GenreTypeEnum
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

class GenreViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: GenreMovieViewModel
    private val getMovies: GetMovies = mockk()
    private val testModule = module { single { getMovies } }

    @Before
    fun setup() {
        startKoin(listOf(testModule))
        viewModel = GenreMovieViewModel(GenreTypeEnum.ACTION)
    }

    @After
    fun reset() {
        stopKoin()
    }

    @Test
    fun `fetchMovieListByGenre WHEN has a error from getMovies MUST post that error`() {
        val exception = IllegalAccessException()
        stubErrorInGetMovies(exception)
        viewModel.fetchMovieListByGenre()
        verify(exactly = 1) { getMovies.execute(any(), any(), any()) }
        assertEquals(
            ViewState(status = ERROR, throwable = exception),
            viewModel.getGenreMoviesViewState().value
        )
    }

    @Test
    fun `fetchMovieListByGenre WHEN has a success from getMovies use case MUST post a list of movies and success status`() {
        val movieList = listOf(Movie(1, "", "", ""))
        val expectedValue = ViewState(
            status = SUCCESS,
            data = movieList.fromDomain()
        )
        stubSuccessInGetMovies(ListPagination(movieList))
        viewModel.fetchMovieListByGenre()
        verify(exactly = 1) { getMovies.execute(any(), any(), any()) }
        assertEquals(expectedValue, viewModel.getGenreMoviesViewState().value)
    }

    @Test
    fun `fetchMovieListByGenre WHEN has call when viewState is loading MUST maintain in loading and not call use case second time`() {
        every { getMovies.execute(any(), any(), any()) } returns Unit
        viewModel.fetchMovieListByGenre()
        viewModel.fetchMovieListByGenre()
        verify(exactly = 1) { getMovies.execute(any(), any(), any()) }
        assertEquals(LOADING, viewModel.getGenreMoviesViewState().value?.status)
    }

    @Test
    fun `refreshMovieList WHEN viewState is loading MUST cancel use case and call him again`() {
        every { getMovies.execute(any(), any(), any()) } returns Unit
        every { getMovies.cancel() } returns Unit
        viewModel.fetchMovieListByGenre()
        viewModel.refreshMovieList()
        verify(exactly = 1) { getMovies.cancel() }
        verify(exactly = 2) { getMovies.execute(any(), any(), any()) }
    }

    @Test
    fun `refreshMovieList WHEN viewState is not loading MUST call getMovies useCase with param page 1`() {
        val expectedParam = GetMovies.Param(GenreTypeParamEnum.ACTION, 1)
        stubSuccessInGetMovies()
        viewModel.refreshMovieList()
        verify(exactly = 1) { getMovies.execute(expectedParam, any(), any()) }
    }

    @Test
    fun `hasMoreResults WHEN called after execute getMovies with success and total page answer is 1 UseCase MUST return false`() {
        stubSuccessInGetMovies(ListPagination(listOf(), totalPage = 1))
        viewModel.fetchMovieListByGenre()
        assertFalse(viewModel.hasMoreResults())
    }

    @Test
    fun `hasMoreResults WHEN call getMovies once success with total page 3 MUST return value true`() {
        stubSuccessInGetMovies(ListPagination(listOf(), totalPage = 3))
        viewModel.fetchMovieListByGenre()
        assertTrue(viewModel.hasMoreResults())
    }

    @Test
    fun `hasMoreResults WHEN has not call once getMovies useCase with success MUST return true`() {
        assertTrue(viewModel.hasMoreResults())
    }


    private fun stubSuccessInGetMovies(data: ListPagination<Movie> = ListPagination(listOf())) {
        every {
            getMovies.execute(any(), onSuccess = captureLambda(), onFailure = any())
        } answers {
            lambda<(ListPagination<Movie>) -> Unit>().invoke(data)
        }
    }

    private fun stubErrorInGetMovies(exception: IllegalAccessException) {
        every {
            getMovies.execute(any(), any(), captureLambda())
        } answers {
            lambda<(Throwable) -> Unit>().invoke(exception)
        }
    }
}