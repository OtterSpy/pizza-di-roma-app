package com.example.pizzadiromaapp.presentation.ui.fragments.productslistfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzadiromaapp.common.Resource
import com.example.pizzadiromaapp.data.repository.ProductRepositoryImpl
import com.example.pizzadiromaapp.domain.model.ProductItem
import com.example.pizzadiromaapp.domain.usecases.GetProductsUseCase
import com.example.pizzadiromaapp.singltone.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val _products = MutableLiveData<Resource<List<ProductItem>>>()
    val products: LiveData<Resource<List<ProductItem>>>
        get() = _products
    var tabPosition: Int? = null

    private val getProductsUseCase =
        GetProductsUseCase(ProductRepositoryImpl(RetrofitClient.pizzaDiRomaApi))

    fun getProducts(type: String) {
        _products.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _products.postValue(Resource.success(getProductsUseCase(type)))
            } catch (t: Throwable) {
                _products.postValue(
                    Resource.error(
                        t.localizedMessage ?: "Unknown error"
                    )
                )
            }
        }
    }
}