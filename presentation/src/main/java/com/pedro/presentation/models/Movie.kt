package com.pedro.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val name: String,
    val image: String?,
    val description: String
) : Parcelable
