package com.pedro.presentation.genre

import com.pedro.domain.models.Movie
import com.pedro.domain.usecases.GetMovies
import com.pedro.presentation.ViewState.Status.*
import com.pedro.presentation.models.GenreTypeEnum
import io.mockk.every
import io.mockk.invoke
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import kotlin.test.assertEquals

class GenreViewModelTest {
    private lateinit var viewModel: GenreMovieViewModel
    private val getMovies: GetMovies = mockk()
    private val testModule = module { single { getMovies } }

    @Before
    fun setup() {
        startKoin(listOf(testModule))
        viewModel = GenreMovieViewModel(GenreTypeEnum.ACTION)
    }

    @Test
    fun `fetchMovieListByGenre WHEN has a error from getMovies MUST post that error`() {
        val exception = IllegalAccessException()
        stubErrorInGetMovies(exception)
        viewModel.fetchMovieListByGenre()
        verify(exactly = 1) { getMovies.execute(any(), any(), any()) }
        assertEquals(ERROR, viewModel.getGenreMoviesViewState().value?.status)
        assertEquals(exception, viewModel.getGenreMoviesViewState().value?.throwable)
    }

    @Test
    fun `fetchMovieListByGenre WHEN has a success from getMovies use case MUST post a list of movies and success status`() {
        val expectedValue = listOf<com.pedro.presentation.models.Movie>()
        stubSuccessInGetMovies()
        viewModel.fetchMovieListByGenre()
        verify(exactly = 1) { getMovies.execute(any(), any(), any()) }
        assertEquals(SUCCESS, viewModel.getGenreMoviesViewState().value?.status)
        assertEquals(expectedValue, viewModel.getGenreMoviesViewState().value?.data)
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
    fun `refreshMovieList WHEN has a error from getMovies MUST post that error`() {
        val exception = IllegalAccessException()
        stubErrorInGetMoviesRefresh(exception)
        viewModel.refreshMovieList()
        verify(exactly = 1) { getMovies.refresh(any(), any(), any()) }
        assertEquals(ERROR, viewModel.getGenreMoviesViewState().value?.status)
        assertEquals(exception, viewModel.getGenreMoviesViewState().value?.throwable)
    }

    @Test
    fun `refreshMovieList WHEN has a success from getMovies use case MUST post a list of movies and success status`() {
        val expectedValue = listOf<com.pedro.presentation.models.Movie>()
        stubSuccessInGetMoviesRefresh()
        viewModel.refreshMovieList()
        verify(exactly = 1) { getMovies.refresh(any(), any(), any()) }
        assertEquals(SUCCESS, viewModel.getGenreMoviesViewState().value?.status)
        assertEquals(expectedValue, viewModel.getGenreMoviesViewState().value?.data)
    }

    @Test
    fun `refreshMovieList WHEN has call when viewState is loading MUST maintain in loading and not call use case second time`() {
        every { getMovies.refresh(any(), any(), any()) } returns Unit
        viewModel.refreshMovieList()
        viewModel.refreshMovieList()
        verify(exactly = 1) { getMovies.refresh(any(), any(), any()) }
        assertEquals(LOADING, viewModel.getGenreMoviesViewState().value?.status)
    }

    private fun stubSuccessInGetMoviesRefresh() {
        every {
            getMovies.refresh(any(), onSuccess = captureLambda(), onFailure = any())
        } answers {
            lambda<(List<Movie>) -> Unit>().invoke(listOf())
        }
    }

    private fun stubErrorInGetMoviesRefresh(exception: IllegalAccessException) {
        every {
            getMovies.refresh(any(), any(), captureLambda())
        } answers {
            lambda<(Throwable) -> Unit>().invoke(exception)
        }
    }

    private fun stubSuccessInGetMovies() {
        every {
            getMovies.execute(any(), onSuccess = captureLambda(), onFailure = any())
        } answers {
            lambda<(List<Movie>) -> Unit>().invoke(listOf())
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