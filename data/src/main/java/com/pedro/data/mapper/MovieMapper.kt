package com.pedro.data.mapper

import com.pedro.data.models.MovieListResponse
import com.pedro.data.models.MovieResponse
import com.pedro.domain.exceptions.MapFailureException
import com.pedro.domain.models.ListPagination
import com.pedro.domain.models.Movie

fun MovieListResponse.toDomain() =
    ListPagination(
        list = movieResponses?.map {
            it.toMovie()
        } ?: listOf(),
        totalPage = totalPages ?: 1
    )

@Throws(MapFailureException::class)
fun MovieResponse.toMovie(): Movie {
    val movieId = mapOrFailureException(id)
    val movieTitle = mapOrFailureException(title)
    val description = mapOrFailureException(overview)
    return Movie(
        id = movieId,
        name = movieTitle,
        image = posterPath,
        description = description
    )
}

@Throws(MapFailureException::class)
fun <T> mapOrFailureException(attr: T?, condition: () -> Boolean = { attr == null }): T {
    if (condition()) throw MapFailureException()
    return attr!!
}