package com.pedro.data_remote.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int? = null,
    val adult: Boolean? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val title: String? = null,
    val video: Boolean? = null,
    @SerializedName(BACKDROP_PATH) val backdropPath: String? = null,
    @SerializedName(GENRE_IDS) val genreIds: List<Int>? = null,
    @SerializedName(ORIGINAL_LANGUAGE) val originalLanguage: String? = null,
    @SerializedName(ORIGINAL_TITLE) val originalTitle: String? = null,
    @SerializedName(POSTER_PATH) val posterPath: String? = null,
    @SerializedName(RELEASE_DATE) val releaseDate: String? = null,
    @SerializedName(VOTE_AVERAGE) val voteAverage: Double? = null,
    @SerializedName(VOTE_COUNT) val vote_count: Int? = null
)