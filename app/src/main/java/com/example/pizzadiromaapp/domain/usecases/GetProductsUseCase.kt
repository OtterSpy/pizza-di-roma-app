package com.example.pizzadiromaapp.domain.usecases

import android.util.Log
import com.example.pizzadiromaapp.domain.repository.ProductRepository
import retrofit2.HttpException

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() {
        try {
            val coins = repository.getProducts("")
        } catch (e: HttpException) {
            Log.e("myLogs", "invoke: GetProductUseCase exception")
        }
    }
}