package com.example.pizzadiromaapp.domain.usecases

import com.example.pizzadiromaapp.domain.model.ProductItem
import com.example.pizzadiromaapp.domain.repository.ProductRepository

class GetProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int): ProductItem {
        return repository.getProductById(id)
    }
}