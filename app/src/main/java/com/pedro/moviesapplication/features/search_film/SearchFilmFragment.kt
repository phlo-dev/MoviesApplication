package com.pedro.moviesapplication.features.search_film

import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.pedro.moviesapplication.R
import com.pedro.moviesapplication.adapter.MovieAdapter
import com.pedro.moviesapplication.base.BaseFragment
import com.pedro.moviesapplication.extensions.*
import com.pedro.presentation.models.Movie
import com.pedro.presentation.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search_film.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFilmFragment : BaseFragment() {
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter { onClickMovie(it) } }
    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search_film, container, false)

    private fun onClickMovie(item: Movie) = navController.safeNavigate(
        SearchFilmFragmentDirections.actionSearchFilmFragmentToFilmDetailsFragment(item)
    )

    override fun setupViews() {
        setupToolbar(R.id.searchToolbar, setUpNavigation = true)
        searchFilmRecyclerView.adapter = movieAdapter
        searchFilmRecyclerView.addScrollListener { lasVisibleItem ->
            if(lasVisibleItem >= movieAdapter.itemCount && viewModel.hasMoreResults()){
                viewModel.search()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        menu.findItem(R.id.item_menu_search_view)?.run {
            (actionView as? SearchView)?.setupSearchView()
            expandActionView()
            setOnActionExpandListener(onActionExpandListener)
        }
    }

    override fun creatingObservers() {
        viewModel.getQueryViewState().handleWithFlow(
            onLoading = { searchProgressBar.isVisible = true },
            onComplete = { searchProgressBar.isVisible = false },
            onSuccess = { movieAdapter.list = it }
        )
    }

    private val onActionExpandListener = object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            navController.safeNavigateUp()
            return true
        }

        override fun onMenuItemActionExpand(item: MenuItem?) = true
    }

    private fun SearchView.setupSearchView() {
        queryHint = getString(R.string.hint_search_film)
        setOnQueryTextListener { viewModel.search(it) }
        setIconifiedByDefault(false)
        setOnSearchClickListener { layoutParams.width = MATCH_PARENT }
    }
}