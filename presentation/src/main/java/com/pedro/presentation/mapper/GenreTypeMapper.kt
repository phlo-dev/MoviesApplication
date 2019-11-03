package com.pedro.presentation.mapper

import com.pedro.domain.models.GenreTypeParamEnum
import com.pedro.presentation.models.GenreTypeEnum

fun GenreTypeEnum.toParam() = when (this) {
    GenreTypeEnum.ACTION -> GenreTypeParamEnum.ACTION
    GenreTypeEnum.DRAMA -> GenreTypeParamEnum.DRAMA
    GenreTypeEnum.FANTASY -> GenreTypeParamEnum.FANTASY
    GenreTypeEnum.FICTION -> GenreTypeParamEnum.FICTION
}