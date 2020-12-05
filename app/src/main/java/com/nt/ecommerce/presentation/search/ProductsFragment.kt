package com.nt.ecommerce.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.nt.ecommerce.databinding.FragmentProductsBinding
import com.nt.ecommerce.presentation.search.adapter.ProductLoadingAdapter
import com.nt.ecommerce.presentation.search.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val searchViewModel: SearchViewModel by activityViewModels()
    private lateinit var binding: FragmentProductsBinding
    private val productsAdapter = ProductsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productsRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = productsAdapter.withLoadStateHeaderAndFooter(
                header = ProductLoadingAdapter { productsAdapter.retry() },
                footer = ProductLoadingAdapter { productsAdapter.retry() }
            )
        }

        binding.upFab.setOnClickListener {
            binding.productsRecyclerview.smoothScrollToPosition(0);
        }
        searchViewModel.products.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                productsAdapter.submitData(it)
            }
        }
        searchViewModel.clearPagedListItems.observe(viewLifecycleOwner){
            productsAdapter.submitData(lifecycle, PagingData.empty())
        }
    }

}