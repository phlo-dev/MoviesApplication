package com.pedro.domain.models

data class ListPagination<T>(
    val list: List<T>,
    val totalPage: Int = 1
)