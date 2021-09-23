package com.example.pizzadiromaapp.presentation.ui.fragments.productslistfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pizzadiromaapp.common.ProductType
import com.example.pizzadiromaapp.common.Resource
import com.example.pizzadiromaapp.databinding.FragmentProductsListBinding
import com.example.pizzadiromaapp.presentation.ui.fragments.productslistfragment.adapter.ProductsRecyclerViewAdapter
import com.google.android.material.tabs.TabLayout

class ProductsListFragment : Fragment() {

    private var _binding: FragmentProductsListBinding? = null
    private val binding get() = _binding!!

    //    private val trep = ProductRepositoryImpl(RetrofitClient.pizzaDiRomaApi)
//    private val getProductsUseCase = GetProductsUseCase(trep)
    private val productsAdapter by lazy { ProductsRecyclerViewAdapter(requireActivity()) }

    private val viewModel: ProductsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsListBinding.inflate(inflater, container, false)

        binding.productListRecyclerView.adapter = productsAdapter
        productsAdapter.setOnItemClickListener { productItem ->
            findNavController().navigate(
                ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(
                    productItem
                )
            )
        }

        setupTabBar()

        checkSelectedTab()

        initObserver()

        loadProducts()

        return binding.root
    }

    private fun setupTabBar() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkSelectedTab() {
        binding.tabBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        1 -> loadProducts(ProductType.PIZZA.text)
                        2 -> loadProducts(ProductType.SUSHI.text)
                        3 -> loadProducts(ProductType.OTHER.text)
                        else -> loadProducts(ProductType.ALL.text)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun initObserver() {
        viewModel.products.observe(viewLifecycleOwner, { resources ->
            when (resources) {
                is Resource.Failure -> {
                    Log.d("QQQ", "resources: $resources")
                }
                is Resource.Loading -> {
                    Log.d("QQQ", "resources: $resources")
                    // show progress bar
                }
                is Resource.Success -> {
                    Log.d("QQQ", "resources: $resources")

                    productsAdapter.submitList(resources.data)

                    Log.d("myLogs", "loadProducts: ")

                }
            }
        })
    }

    private fun loadProducts(type: String = "") {
        viewModel.getProducts(type)
    }
}