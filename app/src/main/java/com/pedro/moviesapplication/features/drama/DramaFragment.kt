package com.pedro.moviesapplication.features.drama

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.extensions.toast
import com.pedro.moviesapplication.utils.SearchItem

class DramaFragment : BaseFragment(), SearchItem {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_drama, container, false)

    override fun search(query: String) {
        toast("${this::class.java.simpleName} -> $query")
    }
}