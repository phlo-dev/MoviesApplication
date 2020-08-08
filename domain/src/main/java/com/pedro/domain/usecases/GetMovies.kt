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
    override suspend fun getResult(param: Param) = when (param.genre) {
        ACTION -> movieRepository.getActionMovies(param.page)
        DRAMA -> movieRepository.getDramaMovies(param.page)
        FANTASY -> movieRepository.getFantasyMovies(param.page)
        FICTION -> movieRepository.getFictionMovies(param.page)
    }

    data class Param(
        val genre: GenreTypeParamEnum,
        val page: Int
    )

}