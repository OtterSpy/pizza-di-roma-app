package com.example.pizzadiromaapp.di

import com.example.pizzadiromaapp.data.repository.ProductRepositoryImpl
import com.example.pizzadiromaapp.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module

@Module
interface RepModule {

    @Binds
    fun bindProductRep(productRepImpl: ProductRepositoryImpl): ProductRepository
}