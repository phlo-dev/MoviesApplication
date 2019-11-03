package com.pedro.presentation.mapper

import com.pedro.domain.models.Movie

fun Movie.fromDomain() = com.pedro.presentation.models.Movie(
    id = id,
    image = image,
    description = description,
    name = name
)

fun List<Movie>.fromDomain() = map { it.fromDomain() }