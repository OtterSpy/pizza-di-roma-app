package com.example.pizzadiromaapp.presentation.ui.fragments.productdetailfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzadiromaapp.common.Resource
import com.example.pizzadiromaapp.data.repository.ProductRepositoryImpl
import com.example.pizzadiromaapp.domain.model.ProductItem
import com.example.pizzadiromaapp.domain.usecases.GetProductUseCase
import com.example.pizzadiromaapp.singltone.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel() {

    private val _product = MutableLiveData<Resource<ProductItem>>()
    val product: LiveData<Resource<ProductItem>>
        get() = _product

    var detailPriceText: String = ""
    var detailNameText: String = ""
    var detailWeightText: String = ""
    var detailDescriptionText: String = ""
    var detailImage: String = ""

    private val getProductUseCase =
        GetProductUseCase(ProductRepositoryImpl(RetrofitClient.pizzaDiRomaApi))

    fun getProductById(id: Int) {
        _product.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getProductUseCase(id).let { productItem ->
                    detailPriceText = buildString {
                        append(productItem.price)
                        append(" грн")
                    }
                    detailNameText = productItem.name
                    detailWeightText = buildString {
                        append(productItem.weight)
                        append(" г")
                    }
                    detailDescriptionText = productItem.description
                    detailImage = productItem.imageUrl
                    _product.postValue(Resource.success(productItem))
                }
                Log.d("myLogs", "getProductById: $detailNameText $detailDescriptionText")
            } catch (t: Throwable) {
                Log.d("myLogs", "getProductByIdError: ")
                _product.postValue(
                    Resource.error(
                        t.localizedMessage ?: "Чё за хуйня блять -_-"
                    )
                )
            }
        }
    }
}