package com.nt.ecommerce.framework.search

import com.nt.ecommerce.framework.network.SearchService
import retrofit2.Response
import javax.inject.Inject


class SearchRepository @Inject constructor(
    private val searchService: SearchService
): SearchUseCase {

    override suspend fun searchForProductsByQuery(
        pageIndex: Int,
        pageSize: Int,
        query: String
    ): Response<List<String>> {
        return searchService.searchForProductsByQuery(pageIndex,pageSize,query)
    }

}