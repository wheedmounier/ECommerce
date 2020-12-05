package com.nt.ecommerce.presentation.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.nt.ecommerce.R
import com.nt.ecommerce.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel:SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.searchLayout.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchView = setupSearchView(menu.findItem(R.id.search_item))
        searchView.setOnQueryTextListener(queryTextListener)
        return super.onCreateOptionsMenu(menu)
    }

    private fun showHideWelcomeMessage(isVisible: Boolean){
        binding.welcomeMessage.isVisible = isVisible
    }

    private fun showHideFragment(isVisible:Boolean){
        binding.navHostFragment.isVisible = isVisible
    }

    private fun setupSearchView(searchViewItem: MenuItem): SearchView {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchViewItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.maxWidth = Integer.MAX_VALUE
        return searchView
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            searchViewModel.resetAndPerformSearch(newText)
            if(newText.isNotEmpty()){
                showHideFragment(true)
                showHideWelcomeMessage(false)
            }
            else{
                searchViewModel.removePagedListData()
//                showHideFragment(false)
//                showHideWelcomeMessage(true)
            }
            return false
        }
    }
}