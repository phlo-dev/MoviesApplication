package com.pedro.domain.repository

import com.pedro.domain.models.ListPagination
import com.pedro.domain.models.Movie
import com.pedro.domain.models.Response

interface MoviesRepository {
    suspend fun getMoviesByCategory(page: Int, categoryId: Int): Response<ListPagination<Movie>>
    suspend fun searchMoviesWithQuery(query: String, page: Int): Response<ListPagination<Movie>>
}