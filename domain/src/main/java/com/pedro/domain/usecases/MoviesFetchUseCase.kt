package com.pedro.domain.usecases

import com.pedro.domain.models.GenreTypeParamEnum
import com.pedro.domain.models.GenreTypeParamEnum.*
import com.pedro.domain.models.ListPagination
import com.pedro.domain.models.Movie
import com.pedro.domain.models.Response
import com.pedro.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope

class MoviesFetchUseCase(
    scope: CoroutineScope,
    private val movieRepository: MoviesRepository
) : UseCase<GenreTypeParamEnum, List<Movie>>(scope) {
    private val movieList = mutableListOf<Movie>()
    private var currentPage = 1
    private var totalPage = 1

    override suspend fun getResult(param: GenreTypeParamEnum): Response<List<Movie>> {
        if (!hasMoreResults()) return Response.Success(listOf())
        return when (param) {
            ACTION -> movieRepository.getActionMovies(currentPage).convertToPresentation()
            DRAMA -> movieRepository.getDramaMovies(currentPage).convertToPresentation()
            FANTASY -> movieRepository.getFantasyMovies(currentPage).convertToPresentation()
            FICTION -> movieRepository.getFictionMovies(currentPage).convertToPresentation()
        }
    }

    fun hasMoreResults() = currentPage <= totalPage

    private suspend fun Response<ListPagination<Movie>>.convertToPresentation() = map {
        movieList.addAll(it.list)
        currentPage++
        totalPage = it.totalPage
        movieList
    }

    fun refresh(
        params: GenreTypeParamEnum,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        movieList.clear()
        currentPage = 1
        execute(params, onSuccess, onFailure)
    }
}