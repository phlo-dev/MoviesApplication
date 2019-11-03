package com.pedro.data.models

data class MovieListResponse(
    val movieResponses: List<MovieResponse>?,
    val totalPages: Int?
)