package com.pedro.data_remote.repository

import com.pedro.data.remote.MovieRemoteRepository
import com.pedro.data_remote.*
import com.pedro.data_remote.mapper.toData
import com.pedro.data_remote.service.MoviesWebService

class MovieRemoteRepositoryImpl(
    private val moviesWebService: MoviesWebService
) : MovieRemoteRepository {
    override suspend fun getActionMovies(page: Int) =
        apiCall { moviesWebService.getMovies(page, ACTION_ID) }.map {
            it.toData()
        }

    override suspend fun getDramaMovies(page: Int) =
        apiCall { moviesWebService.getMovies(page, DRAMA_ID) }.map {
            it.toData()
        }

    override suspend fun getFantasyMovies(page: Int) =
        apiCall { moviesWebService.getMovies(page, FANTASY_ID) }.map {
            it.toData()
        }

    override suspend fun getFictionMovies(page: Int) =
        apiCall { moviesWebService.getMovies(page, FICTION_ID) }.map {
            it.toData()
        }

    override suspend fun searchMoviesWithQuery(query: String, page: Int) =
        apiCall { moviesWebService.searchMovieByQuery(query, page) }.map {
            it.toData()
        }
}