package com.example.pizzadiromaapp.data.repository

import com.example.pizzadiromaapp.data.remote.PizzaDiRomaApi
import com.example.pizzadiromaapp.data.remote.dto.toProductItem
import com.example.pizzadiromaapp.domain.model.ProductItem
import com.example.pizzadiromaapp.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val pizzaDiRomaApi: PizzaDiRomaApi
) : ProductRepository {

    override suspend fun getProducts(type: String): List<ProductItem> =
        pizzaDiRomaApi.getProducts(type)
            .filter { it.type != "OTHER" }
            .map { it.toProductItem() }

    override suspend fun getProductById(productId: Int): ProductItem =
        pizzaDiRomaApi.getProductDetail(productId).toProductItem()

}