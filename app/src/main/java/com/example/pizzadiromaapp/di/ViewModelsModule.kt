package com.example.pizzadiromaapp.di

import androidx.lifecycle.ViewModel
import com.example.pizzadiromaapp.presentation.ui.fragments.productdetailfragment.ProductDetailViewModel
import com.example.pizzadiromaapp.presentation.ui.fragments.productslistfragment.ProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {

    @Binds
    @[IntoMap ClassKey(ProductsViewModel::class)]
    fun provideProductsViewModel(productsViewModel: ProductsViewModel): ViewModel

    @Binds
    @[IntoMap ClassKey(ProductDetailViewModel::class)]
    fun provideProductDetailsViewModel(productsViewModel: ProductDetailViewModel): ViewModel
}