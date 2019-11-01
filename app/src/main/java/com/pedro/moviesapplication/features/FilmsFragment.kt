package com.pedro.moviesapplication.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.adapter.ViewPagerAdapter
import com.pedro.moviesapplication.base.BaseFragment
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
        val fragmentList = listOf(
            ActionFragment() to getString(R.string.action_label),
            DramaFragment() to getString(R.string.drama_label),
            FantasyFragment() to getString(R.string.fantasy_label),
            FictionFragment() to getString(R.string.fiction_label)
        )
        filmViewPager.adapter = ViewPagerAdapter(childFragmentManager, fragmentList)
        filmTabLayout.setupWithViewPager(filmViewPager)
    }
}