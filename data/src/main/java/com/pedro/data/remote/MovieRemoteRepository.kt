package com.pedro.data.remote

import com.pedro.data.models.MovieListResponse
import com.pedro.domain.models.Response

interface MovieRemoteRepository {
    suspend fun getActionMovies(page: Int): Response<MovieListResponse>
    suspend fun getDramaMovies(page: Int): Response<MovieListResponse>
    suspend fun getFantasyMovies(page: Int): Response<MovieListResponse>
    suspend fun getFictionMovies(page: Int): Response<MovieListResponse>
    suspend fun searchMoviesWithQuery(query: String, page: Int): Response<MovieListResponse>
}