package com.pedro.domain.usecases

import com.pedro.domain.models.ListPagination
import com.pedro.domain.models.Movie
import com.pedro.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope

class SearchMovies(
    scope: CoroutineScope,
    private val repository: MoviesRepository
) : UseCase<SearchMovies.Param, ListPagination<Movie>>(scope) {

    override suspend fun getResult(param: Param) = repository.searchMoviesWithQuery(
        query = param.query,
        page = param.page
    )

    data class Param(
        val query: String,
        val page: Int
    )

}