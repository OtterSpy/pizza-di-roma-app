package com.example.pizzadiromaapp.domain.model

import java.io.Serializable

data class ProductItem(
    val description: String,
    val id: Int,
    val imageUrl: String,
    val name: String,
    val price: Double,
    val type: String,
    val weight: Int
) : Serializable