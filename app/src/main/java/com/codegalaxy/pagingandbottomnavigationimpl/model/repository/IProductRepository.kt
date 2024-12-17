package com.codegalaxy.pagingimpldec16.model.repository

import androidx.paging.PagingData
import com.codegalaxy.pagingimpldec16.model.dto.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getProductsFlow(): Flow<PagingData<Product>>
}