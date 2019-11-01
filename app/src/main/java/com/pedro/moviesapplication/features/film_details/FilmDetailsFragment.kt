package com.pedro.moviesapplication.features.film_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.base.BaseFragment

class FilmDetailsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_film_details, container, false)
}