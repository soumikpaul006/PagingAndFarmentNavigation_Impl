package com.codegalaxy.pagingimpldec16.model.dto

data class ProductData(
    val TOTAL_PAGES: Int,
    val TOTAL_RECORDS: Int,
    val CURRENT_PAGE_NO: Int,
    val PRODUCT_LIST: List<Product>
)
