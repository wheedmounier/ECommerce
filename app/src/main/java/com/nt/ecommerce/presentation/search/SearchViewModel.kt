package com.nt.ecommerce.presentation.search

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.*
import com.nt.ecommerce.framework.search.ProductPagingSource
import com.nt.ecommerce.framework.search.SearchUseCase
import kotlinx.coroutines.flow.Flow

class SearchViewModel @ViewModelInject constructor(
    private val searchUseCase: SearchUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle

):ViewModel(){

    private var _queryString = MutableLiveData<String>()
    val queryString = _queryString

    private var _clearPagedListItems = MutableLiveData<Unit>()
    val clearPagedListItems = _clearPagedListItems

    private val pagingConfig = PagingConfig(pageSize = 10,enablePlaceholders = false,initialLoadSize = 10)

    val products = MediatorLiveData<PagingData<String>>()

    fun resetAndPerformSearch(query: String) {
        removePagedListData()
        _queryString.postValue(query)
        products.addSource(
            Pager(pagingConfig) {
            ProductPagingSource(query,searchUseCase)
        }.liveData.cachedIn(viewModelScope)
        ) {
            products.postValue(it)
        }

    }
    fun removePagedListData(){
        _clearPagedListItems.postValue(Unit)
    }


}