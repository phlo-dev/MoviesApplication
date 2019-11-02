package com.pedro.data_remote.models

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val page: Int? = null,
    val movieResponses: List<MovieResponse>? = null,
    @SerializedName(TOTAL_PAGE) val totalPages: Int? = null,
    @SerializedName(TOTAL_RESULTS) val totalResults: Int? = null
)