package com.pedro.data_remote.mapper

import com.pedro.data.models.MovieResponse
import com.pedro.data_remote.models.MovieListResponse

fun MovieListResponse.toData() = com.pedro.data.models.MovieListResponse(
    movieResponses = movieResponses?.map {
        MovieResponse(
            id = it.id,
            overview = it.overview,
            title = it.title,
            posterPath = it.posterPath
        )
    } ?: listOf(),
    totalPages = totalPages
)