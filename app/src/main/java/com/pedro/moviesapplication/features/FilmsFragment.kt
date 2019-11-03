package com.pedro.moviesapplication.features

import android.os.Bundle
import android.view.*
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.adapter.GenreViewPagerAdapter
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.extensions.safeNavigate
import com.pedro.moviesapplication.extensions.setupToolbar
import com.pedro.presentation.models.GenreTypeEnum.*
import kotlinx.android.synthetic.main.fragment_films.*

class FilmsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_films, container, false)

    override fun setupViews() {
        setupToolbar(R.id.filmToolbar, R.string.movies_label)
        val fragmentHelperList = listOf(
            ACTION to getString(R.string.action_label),
            DRAMA to getString(R.string.drama_label),
            FANTASY to getString(R.string.fantasy_label),
            FICTION to getString(R.string.fiction_label)
        )
        filmViewPager.adapter = GenreViewPagerAdapter(childFragmentManager, fragmentHelperList)
        filmTabLayout.setupWithViewPager(filmViewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_menu_search) navController.safeNavigate(
            FilmsFragmentDirections.actionFilmsFragmentToSearchFilmFragment()
        )
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_film, menu)
    }

}