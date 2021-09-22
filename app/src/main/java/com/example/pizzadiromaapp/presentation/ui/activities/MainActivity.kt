package com.example.pizzadiromaapp.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pizzadiromaapp.R
import com.example.pizzadiromaapp.data.repository.ProductRepositoryImpl
import com.example.pizzadiromaapp.singltone.RetrofitClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val test = Test.createFrom()

        val productRep = ProductRepositoryImpl(RetrofitClient.pizzaDiRomaApi)
    }

    class Test() {
        companion object {
            fun createFrom() = Test()
        }
    }
}