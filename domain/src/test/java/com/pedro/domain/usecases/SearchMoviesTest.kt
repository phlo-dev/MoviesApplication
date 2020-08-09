package com.pedro.domain.usecases

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
import org.koin.standalone.StandAloneContext
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class SearchMoviesTest {
    private lateinit var subject: SearchMovies
    private val moviesRepository: MoviesRepository = mockk()

    @Before
    fun setup() {
        StandAloneContext.startKoin(listOf(testModule))
        subject = SearchMovies(CoroutineScope(Dispatchers.Unconfined), moviesRepository)
    }

    @After
    fun reset() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun `WHEN has a error in searchMovies on repository MUST return that error as response Failure`() {
        val expected = Exception()
        stubErrorInSearchMoviesRepositoryCall(expected)
        runBlocking {
            val result = subject.getResult(SearchMovies.Param("", 1))
            assertTrue(result is Response.Failure && expected == result.throwable)
        }
    }

    @Test
    fun `WHEN has a success call on searchMovies from repository MUST return that data as Response Success`(){
        val expectedData = ListPagination<Movie>(listOf())
        stubSuccessInSearchMoviesRepositoryCall(expectedData)
        runBlocking {
            val result = subject.getResult(SearchMovies.Param("", 1))
            assertTrue(result is Response.Success && result.data == expectedData)
        }
    }

    private fun stubSuccessInSearchMoviesRepositoryCall(data: ListPagination<Movie>) {
        every {
            runBlocking { moviesRepository.searchMoviesWithQuery(any(), any()) }
        } answers {
            Response.Success(data)
        }
    }

    private fun stubErrorInSearchMoviesRepositoryCall(expected: Exception) {
        every {
            runBlocking { moviesRepository.searchMoviesWithQuery(any(), any()) }
        } answers {
            Response.Failure(expected)
        }
    }
}