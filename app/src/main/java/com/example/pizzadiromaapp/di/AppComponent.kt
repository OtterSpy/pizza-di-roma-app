package com.example.pizzadiromaapp.di

import com.example.pizzadiromaapp.presentation.ui.fragments.ViewModelFactory
import com.example.pizzadiromaapp.presentation.ui.fragments.productdetailfragment.ProductDetailsFragment
import com.example.pizzadiromaapp.presentation.ui.fragments.productslistfragment.ProductsListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepModule::class, ViewModelsModule::class])
interface AppComponent {

    val factory: ViewModelFactory

    fun inject(productDetailsFragment: ProductDetailsFragment)
    fun inject(productDetailsFragment: ProductsListFragment)
}

