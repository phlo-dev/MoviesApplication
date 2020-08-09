package com.pedro.data_remote.repository

import com.pedro.data.remote.MovieRemoteRepository
import com.pedro.data_remote.*
import com.pedro.data_remote.mapper.toData
import com.pedro.data_remote.service.MoviesWebService

class MovieRemoteRepositoryImpl(
    private val moviesWebService: MoviesWebService
) : MovieRemoteRepository {
    override suspend fun getCategoryMovies(page: Int, categoryId: Int) =
        apiCall { moviesWebService.getMovies(page, categoryId) }.map {
            it.toData()
        }

    override suspend fun searchMoviesWithQuery(query: String, page: Int) =
        apiCall { moviesWebService.searchMovieByQuery(query, page) }.map {
            it.toData()
        }
}