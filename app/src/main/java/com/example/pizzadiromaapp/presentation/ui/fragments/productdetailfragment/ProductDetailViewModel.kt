package com.example.pizzadiromaapp.presentation.ui.fragments.productdetailfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzadiromaapp.common.Resource
import com.example.pizzadiromaapp.domain.model.ProductItem
import com.example.pizzadiromaapp.domain.usecases.GetProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(private val getProductUseCase: GetProductUseCase) :
    ViewModel() {

    private val _product = MutableLiveData<Resource<ProductItem>>()
    val product: LiveData<Resource<ProductItem>>
        get() = _product

    fun getProductById(id: Int) {
        _product.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getProductUseCase(id).let { productItem ->
                    _product.postValue(Resource.success(productItem))
                }
            } catch (t: Throwable) {
                _product.postValue(
                    Resource.error(
                        t.localizedMessage ?: "Unknown error"
                    )
                )
            }
        }
    }
}