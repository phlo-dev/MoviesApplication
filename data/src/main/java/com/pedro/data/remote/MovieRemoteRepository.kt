package com.pedro.data.remote

import com.pedro.data.models.MovieListResponse
import com.pedro.domain.models.Response

interface MovieRemoteRepository {
    suspend fun getMoviesByCategory(page: Int, categoryId: Int): Response<MovieListResponse>
    suspend fun searchMoviesWithQuery(query: String, page: Int): Response<MovieListResponse>
}