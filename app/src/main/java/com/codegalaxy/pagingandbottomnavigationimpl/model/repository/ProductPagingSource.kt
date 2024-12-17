package com.codegalaxy.pagingimpldec16.model.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codegalaxy.pagingimpldec16.model.dto.Product
import com.codegalaxy.pagingimpldec16.model.dto.ProductSearchRequest
import com.codegalaxy.pagingimpldec16.model.network.ApiService

class ProductPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentPage = params.key ?: 1
            val requestBody = ProductSearchRequest(
                categoryCode = listOf("AA"),
                pageNumber = currentPage,
                pageSize = params.loadSize
            )

            val response = apiService.getProducts(requestBody)

            val data = response.data.PRODUCT_LIST

            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage < response.data.TOTAL_PAGES) currentPage + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}