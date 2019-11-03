package com.pedro.presentation.models

enum class GenreTypeEnum {
    ACTION, DRAMA, FANTASY, FICTION;

    companion object {
        fun getGenre(position: Int = 0) = when (position) {
            1 -> DRAMA
            2 -> FANTASY
            3 -> FICTION
            else -> ACTION
        }
    }
}