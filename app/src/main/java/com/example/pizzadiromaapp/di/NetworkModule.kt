package com.example.pizzadiromaapp.di

import com.example.pizzadiromaapp.common.Constants
import com.example.pizzadiromaapp.data.remote.PizzaDiRomaApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providePizzaApi(retrofit: Retrofit): PizzaDiRomaApi {
        return retrofit.create(PizzaDiRomaApi::class.java)
    }
}