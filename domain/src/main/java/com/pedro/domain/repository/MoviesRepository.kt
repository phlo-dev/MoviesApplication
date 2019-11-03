package com.pedro.domain.repository

import com.pedro.domain.models.ListPagination
import com.pedro.domain.models.Movie
import com.pedro.domain.models.Response

interface MoviesRepository {
    suspend fun getActionMovies(page: Int): Response<ListPagination<Movie>>
    suspend fun getDramaMovies(page: Int): Response<ListPagination<Movie>>
    suspend fun getFantasyMovies(page: Int): Response<ListPagination<Movie>>
    suspend fun getFictionMovies(page: Int): Response<ListPagination<Movie>>
    suspend fun searchMoviesWithQuery(query: String, page: Int): Response<ListPagination<Movie>>
}