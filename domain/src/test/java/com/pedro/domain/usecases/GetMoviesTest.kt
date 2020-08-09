package com.pedro.domain.usecases

import com.pedro.domain.models.GenreTypeParamEnum
import com.pedro.domain.models.ListPagination
import com.pedro.domain.models.Movie
import com.pedro.domain.models.Response
import com.pedro.domain.repository.MoviesRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetMoviesTest {
    private lateinit var subject: GetMovies
    private val moviesRepository: MoviesRepository = mockk()

    @Before
    fun setup() {
        startKoin(listOf(testModule))
        subject = GetMovies(CoroutineScope(Dispatchers.Unconfined), moviesRepository)
    }

    @After
    fun reset() {
        stopKoin()
    }

    @Test
    fun `WHEN has a error in repository call MUST return that exception as a Response Failure`() {
        val expected = IllegalAccessError()
        stubErrorInGetMoviesByCategory(expected)
        runBlocking {
            val result = subject.getResult(GetMovies.Param(GenreTypeParamEnum.ACTION, 1))
            assertTrue(result is Response.Failure && result.throwable == expected)
        }
    }

    @Test
    fun `WHEN has a success call in getMoviesByCategory MUST return the data as as ResponseSuccess`() {
        stubSuccessInGetMoviesByCategory()
        runBlocking {
            val result = subject.getResult(GetMovies.Param(GenreTypeParamEnum.ACTION, 1))
            assertTrue(result is Response.Success<ListPagination<Movie>>)
        }
    }

    private fun stubErrorInGetMoviesByCategory(expected: IllegalAccessError) {
        every {
            runBlocking { moviesRepository.getMoviesByCategory(any(), any()) }
        } answers {
            Response.Failure(expected)
        }
    }

    private fun stubSuccessInGetMoviesByCategory() {
        every {
            runBlocking { moviesRepository.getMoviesByCategory(any(), any()) }
        } answers {
            Response.Success(ListPagination(listOf()))
        }
    }
}