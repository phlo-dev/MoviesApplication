package com.pedro.data_remote.mapper

import com.pedro.data.models.MovieResponse
import com.pedro.data_remote.BASE_IMAGE_URL
import com.pedro.data_remote.FILE_SIZE
import com.pedro.data_remote.models.MovieListResponse

fun MovieListResponse.toData() = com.pedro.data.models.MovieListResponse(
    movieResponses = movieResponses?.map {
        MovieResponse(
            id = it.id,
            overview = it.overview,
            title = it.title,
            posterPath = buildImagePath(it.posterPath)
        )
    } ?: listOf(),
    totalPages = totalPages
)

fun buildImagePath(path: String?) = when(path.isNullOrBlank()){
    true -> path
    else -> "$BASE_IMAGE_URL/$FILE_SIZE/$path"
}