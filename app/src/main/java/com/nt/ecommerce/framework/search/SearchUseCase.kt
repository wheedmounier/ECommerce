package com.nt.ecommerce.framework.search

import retrofit2.Response

interface SearchUseCase {
    suspend fun searchForProductsByQuery(pageIndex:Int, pageSize:Int, query:String) : Response<List<String>>

}