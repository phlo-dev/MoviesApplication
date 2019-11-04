package com.pedro.data.repository

import com.pedro.data.mapper.toDomain
import com.pedro.data.models.MovieListResponse
import com.pedro.data.remote.MovieRemoteRepository
import com.pedro.domain.exceptions.MapFailureException
import com.pedro.domain.models.Response
import com.pedro.domain.repository.MoviesRepository

class MovieRepositoryImpl(private val remoteRepository: MovieRemoteRepository) : MoviesRepository {
    override suspend fun getActionMovies(page: Int) =
        remoteRepository.getActionMovies(page).toDomain()

    override suspend fun getDramaMovies(page: Int) =
        remoteRepository.getDramaMovies(page).toDomain()

    override suspend fun getFantasyMovies(page: Int) =
        remoteRepository.getFantasyMovies(page).toDomain()

    override suspend fun getFictionMovies(page: Int) =
        remoteRepository.getFictionMovies(page).toDomain()

    override suspend fun searchMoviesWithQuery(query: String, page: Int) =
        remoteRepository.searchMoviesWithQuery(query, page).toDomain()

    private suspend fun Response<MovieListResponse>.toDomain() = flatMap {
        try {
            Response.Success(it.toDomain())
        } catch (e: MapFailureException) {
            Response.Failure(e)
        }
    }
}