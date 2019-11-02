package com.pedro.moviesapplication.features

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.adapter.ViewPagerAdapter
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.extensions.setOnQueryTextListener
import com.pedro.moviesapplication.extensions.setupToolbar
import com.pedro.moviesapplication.extensions.toast
import com.pedro.moviesapplication.features.action.ActionFragment
import com.pedro.moviesapplication.features.drama.DramaFragment
import com.pedro.moviesapplication.features.fantasy.FantasyFragment
import com.pedro.moviesapplication.features.fiction.FictionFragment
import kotlinx.android.synthetic.main.fragment_films.*

class FilmsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_films, container, false)

    override fun setupViews() {
        setupToolbar(R.id.filmToolbar, R.string.movies_label)
        val fragmentList = listOf(
            ActionFragment() as BaseFragment to getString(R.string.action_label),
            DramaFragment() as BaseFragment to getString(R.string.drama_label),
            FantasyFragment() as BaseFragment to getString(R.string.fantasy_label),
            FictionFragment() as BaseFragment to getString(R.string.fiction_label)
        )
        filmViewPager.adapter = ViewPagerAdapter(childFragmentManager, fragmentList)
        filmTabLayout.setupWithViewPager(filmViewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_film, menu)
    }

}