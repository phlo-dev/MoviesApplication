package com.pedro.data.repository

import com.pedro.data.mapper.toDomain
import com.pedro.data.remote.MovieRemoteRepository
import com.pedro.domain.repository.MoviesRepository

class MovieRepositoryImpl(private val remoteRepository: MovieRemoteRepository) : MoviesRepository {
    override suspend fun getActionMovies(page: Int) = remoteRepository.getActionMovies(page).map {
        it.toDomain()
    }

    override suspend fun getDramaMovies(page: Int) = remoteRepository.getDramaMovies(page).map {
        it.toDomain()
    }

    override suspend fun getFantasyMovies(page: Int) = remoteRepository.getFantasyMovies(page).map {
        it.toDomain()
    }

    override suspend fun getFictionMovies(page: Int) = remoteRepository.getFictionMovies(page).map {
        it.toDomain()
    }

    override suspend fun searchMoviesWithQuery(query: String, page: Int) =
        remoteRepository.searchMoviesWithQuery(query, page).map {
            it.toDomain()
        }
}