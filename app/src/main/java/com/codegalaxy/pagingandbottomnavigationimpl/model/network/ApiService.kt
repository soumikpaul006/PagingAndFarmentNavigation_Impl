package com.codegalaxy.pagingimpldec16.model.network

import com.codegalaxy.pagingimpldec16.model.dto.ProductResponse
import com.codegalaxy.pagingimpldec16.model.dto.ProductSearchRequest
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    @POST("crm-common/api/common/msil/partmaster/search-product-list")
    suspend fun getProducts(
        @Body request: ProductSearchRequest
    ): ProductResponse
}