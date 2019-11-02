package com.pedro.moviesapplication.features.search_film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.adapter.FilmAdapter
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.extensions.safeNavigate
import com.pedro.moviesapplication.extensions.setOnQueryTextListener
import com.pedro.moviesapplication.features.FilmsFragmentDirections
import com.pedro.presentation.models.Film
import kotlinx.android.synthetic.main.fragment_search_film.*

class SearchFilmFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search_film, container, false)

    private fun onClickFilm(item: Film) = navController.safeNavigate(
        SearchFilmFragmentDirections.actionSearchFilmFragmentToFilmDetailsFragment(item)
    )

    override fun setupViews() {
        filmSearchView.setupSearchView()
        searchFilmRecyclerView.adapter = FilmAdapter(listOf()) { onClickFilm(it) }
    }

    private fun SearchView.setupSearchView() {
        queryHint = getString(R.string.hint_search_film)
        setOnQueryTextListener { query ->

        }
    }
}