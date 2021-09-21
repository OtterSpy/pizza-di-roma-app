package com.example.pizzadiromaapp.data.remote.dto

import com.example.pizzadiromaapp.domain.model.ProductItem

data class ProductsListDtoItem(
    val active: Boolean,
    val description: String,
    val id: Int,
    val imageId: String,
    val imageUrl: String,
    val name: String,
    val price: Double,
    val type: String,
    val weight: Int
)

fun ProductsListDtoItem.toProductItem() =
    ProductItem(description, id, imageUrl, name, price, type, weight)