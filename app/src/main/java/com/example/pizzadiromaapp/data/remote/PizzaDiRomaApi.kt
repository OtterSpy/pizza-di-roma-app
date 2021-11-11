package com.example.pizzadiromaapp.data.remote

import com.example.pizzadiromaapp.data.remote.dto.ProductsListDtoItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PizzaDiRomaApi {

    @GET("api/v1/products")
    suspend fun getProducts(@Query("type") type: String): List<ProductsListDtoItem>

    @GET("api/v1/products/{id}")
    suspend fun getProductDetail(@Path("id") productId: Int): ProductsListDtoItem

}