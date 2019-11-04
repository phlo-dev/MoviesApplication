package com.pedro.moviesapplication.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pedro.moviesapplication.fragments.genre.GenreMovieFragment
import com.pedro.presentation.models.GenreTypeEnum

class GenreViewPagerAdapter(
    fm: FragmentManager,
    private val fragmentList: List<Pair<GenreTypeEnum, CharSequence>>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getPageTitle(position: Int): CharSequence = fragmentList[position].second

    override fun getItem(position: Int) =
        GenreMovieFragment.newInstance(GenreTypeEnum.getGenre(position))

    override fun getCount(): Int = fragmentList.size
}
