package com.nt.ecommerce.framework.network

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface SearchService {

    @POST("search")
    suspend fun searchForProductsByQuery(@Query("pageIndex")pageIndex:Int,
                                         @Query("pageSize")pageSize:Int,
                                         @Query("query")query:String): Response<List<String>>

}