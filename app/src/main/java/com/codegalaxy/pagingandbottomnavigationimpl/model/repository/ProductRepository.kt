package com.codegalaxy.pagingimpldec16.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codegalaxy.pagingimpldec16.model.dto.Product
import com.codegalaxy.pagingimpldec16.model.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) : IProductRepository {

    override fun getProductsFlow(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductPagingSource(apiService) }
        ).flow
    }
}