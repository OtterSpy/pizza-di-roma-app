package com.example.pizzadiromaapp.domain.repository

import com.example.pizzadiromaapp.domain.model.ProductItem

interface ProductRepository {

    suspend fun getProducts(type: String): List<ProductItem>

    suspend fun getProductById(productId: Int): ProductItem

}