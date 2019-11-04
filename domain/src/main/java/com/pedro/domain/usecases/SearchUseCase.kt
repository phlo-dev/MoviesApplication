package com.pedro.domain.usecases

import com.pedro.domain.models.ListPagination
import com.pedro.domain.models.Movie
import com.pedro.domain.models.Response
import com.pedro.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope

class SearchUseCase(
    scope: CoroutineScope,
    private val repository: MoviesRepository
) : UseCase<String, List<Movie>>(scope) {
    private val movieList = mutableListOf<Movie>()
    private var query = ""
    private var currentPage = 1
    private var totalPage = 1

    fun hasMoreResults() = currentPage <= totalPage

    private suspend fun Response<ListPagination<Movie>>.convertToPresentation() = map {
        incrementingPagination(it)
        movieList
    }

    private fun incrementingPagination(it: ListPagination<Movie>) {
        movieList.addAll(it.list)
        currentPage++
        totalPage = it.totalPage
    }

    override suspend fun getResult(param: String): Response<List<Movie>> {
        checkIfIsRefresh(param)
        return when (hasMoreResults() && param.isNotBlank()) {
            true -> repository.searchMoviesWithQuery(
                query = param,
                page = currentPage
            ).convertToPresentation()
            false -> Response.Success(listOf())
        }
    }

    private fun checkIfIsRefresh(param: String) {
        if(param == query) return
        query = param
        currentPage = 1
        totalPage = 1
        movieList.clear()
    }
}