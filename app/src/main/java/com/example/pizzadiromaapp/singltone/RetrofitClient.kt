package com.example.pizzadiromaapp.singltone

import com.example.pizzadiromaapp.common.Constants
import com.example.pizzadiromaapp.data.remote.PizzaDiRomaApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val client = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val pizzaDiRomaApi = client.create(PizzaDiRomaApi::class.java)
}