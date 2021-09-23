package com.example.pizzadiromaapp.data.repository

import com.example.pizzadiromaapp.data.remote.PizzaDiRomaApi
import com.example.pizzadiromaapp.data.remote.dto.toProductItem
import com.example.pizzadiromaapp.domain.model.ProductItem
import com.example.pizzadiromaapp.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val pizzaDiRomaApi: PizzaDiRomaApi
) : ProductRepository {

    override suspend fun getProducts(type: String): List<ProductItem> =
        pizzaDiRomaApi.getProducts(type).map { it.toProductItem() }

    override suspend fun getProductById(productId: Int): ProductItem =
        pizzaDiRomaApi.getProductDetail(productId).toProductItem()

}