package com.pedro.moviesapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pedro.moviesapplication.base.BaseFragment

class ViewPagerAdapter(
    fm: FragmentManager,
    private val fragmentList: List<Pair<BaseFragment, CharSequence>>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getPageTitle(position: Int): CharSequence = fragmentList[position].second

    override fun getItem(position: Int): Fragment = fragmentList[position].first

    override fun getCount(): Int = fragmentList.size
}
