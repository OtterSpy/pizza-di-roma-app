package com.example.pizzadiromaapp.domain.usecases

import com.example.pizzadiromaapp.domain.model.ProductItem
import com.example.pizzadiromaapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(type: String): List<ProductItem> {
        return repository.getProducts(type)
    }
}