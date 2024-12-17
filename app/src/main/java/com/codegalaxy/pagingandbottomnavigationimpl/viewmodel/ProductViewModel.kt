package com.codegalaxy.pagingimpldec16.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codegalaxy.pagingimpldec16.model.dto.Product
import com.codegalaxy.pagingimpldec16.model.repository.IProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: IProductRepository
) : ViewModel() {

    val products: Flow<PagingData<Product>> = repository.getProductsFlow()
        .cachedIn(viewModelScope)
}
