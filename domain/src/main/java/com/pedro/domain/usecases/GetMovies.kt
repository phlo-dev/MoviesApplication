package com.pedro.domain.usecases

import com.pedro.domain.models.GenreTypeParamEnum
import com.pedro.domain.models.GenreTypeParamEnum.*
import com.pedro.domain.models.ListPagination
import com.pedro.domain.models.Movie
import com.pedro.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope

class GetMovies(
    scope: CoroutineScope,
    private val movieRepository: MoviesRepository
) : UseCase<GetMovies.Param, ListPagination<Movie>>(scope) {
    override suspend fun getResult(param: Param) =
        movieRepository.getCategoryMovies(page = param.page, categoryId = param.genre.categoryId)

    data class Param(
        val genre: GenreTypeParamEnum,
        val page: Int
    )

}