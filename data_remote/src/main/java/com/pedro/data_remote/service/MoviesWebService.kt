package com.pedro.data_remote.service

import com.pedro.data_remote.*
import com.pedro.data_remote.models.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesWebService {
    @GET(MOVIES_PATH)
    fun getMovies(
        @Query(PAGE_QUERY) page: Int,
        @Query(GENRE_MOVIES_QUERY) genreId: Int,
        @Query(LANGUAGE_QUERY) language: String = LANGUAGE_PT_VALUE
    ): MovieListResponse
}