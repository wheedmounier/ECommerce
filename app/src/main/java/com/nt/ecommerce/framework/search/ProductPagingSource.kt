package com.nt.ecommerce.framework.search

import androidx.paging.PagingSource

class ProductPagingSource(
    private val query: String,
    private val searchUseCase: SearchUseCase,
) : PagingSource<Int, String>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        val nextPage = params.key ?: 1
        if (nextPage < 3) {
            return try {
                val response =
                    searchUseCase.searchForProductsByQuery(nextPage, params.pageSize, query)

                LoadResult.Page(
                    data = response.body() ?: loadResult(nextPage, params.loadSize, query),
                    prevKey = null,
                    nextKey = nextPage + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        } else {
            return LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null,
            )
        }

    }

    private fun loadResult(pageIndex: Int, pageSize: Int, query: String): List<String> {
        val result = listOf(
            "somestring1",
            "somestring2",
            "somestring3",
            "somestring4",
            "somestring5",
            "somestring6",
            "somestring7",
            "somestring8",
            "somestring9",
            "somestring10",
            "somestring11",
            "somestring12",
            "somestring13",
            "somestring14",
            "somestring15",
            "somestring16",
            "somestring17",
            "somestring18",
            "somestring19",
            "somestring20",
            "somestring21",
            "somestring22",
            "somestring23",
            "somestring24",
            "somestring25",
            "somestring26",
            "somestring27",
            "somestring28",
            "somestring29",
            "somestring30",
            "somestring31",
            "somestring32",
            "somestring33",
            "somestring34",
            "somestring35",
            "somestring36",
            "somestring37",
            "somestring38",
            "somestring39",
            "somestring40",
            "somestring41",
            "somestring42",
            "somestring43",
            "somestring44"
        )
        val mappedResult = result.filter { it.contains(query) }

        return try {
            val endIndex = pageIndex * pageSize
            val startIndex = endIndex - pageSize
            if(mappedResult.size > endIndex){
                mappedResult.subList(startIndex, endIndex)
            } else{
                mappedResult.subList(startIndex, mappedResult.size)
            }
        } catch (e:Exception){
            listOf()
        }


    }
}