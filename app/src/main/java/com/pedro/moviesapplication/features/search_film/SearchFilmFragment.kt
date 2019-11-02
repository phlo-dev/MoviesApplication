package com.pedro.moviesapplication.features.search_film

import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.appcompat.widget.SearchView
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.adapter.FilmAdapter
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.extensions.safeNavigate
import com.pedro.moviesapplication.extensions.setOnQueryTextListener
import com.pedro.moviesapplication.extensions.setupToolbar
import com.pedro.moviesapplication.extensions.toast
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
        setupToolbar(R.id.searchToolbar, setUpNavigation = true)
        searchFilmRecyclerView.adapter = FilmAdapter(listOf()) { onClickFilm(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        menu.findItem(R.id.item_menu_search_view)?.run {
            (actionView as? SearchView)?.setupSearchView()
            expandActionView()
        }
    }

    private fun SearchView.setupSearchView() {
        queryHint = getString(R.string.hint_search_film)
        setOnQueryTextListener { query ->
            toast(query)
        }
        setOnCloseListener {
            navController.navigateUp()
            isEnabled = false
            true
        }
        setOnSearchClickListener { layoutParams.width = MATCH_PARENT }
        setIconifiedByDefault(false)
        isIconified = true
    }
}