package com.pedro.data.repository

import com.pedro.data.mapper.toDomain
import com.pedro.data.models.MovieListResponse
import com.pedro.data.remote.MovieRemoteRepository
import com.pedro.domain.exceptions.MapFailureException
import com.pedro.domain.models.ListPagination
import com.pedro.domain.models.Movie
import com.pedro.domain.models.Response
import com.pedro.domain.repository.MoviesRepository

class MovieRepositoryImpl(private val remoteRepository: MovieRemoteRepository) : MoviesRepository {

    override suspend fun getCategoryMovies(page: Int, categoryId: Int) =
        remoteRepository.getCategoryMovies(page, categoryId).toDomain()

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