package com.example.pizzadiromaapp.presentation.ui.fragments.productslistfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pizzadiromaapp.common.ProductType
import com.example.pizzadiromaapp.common.Resource
import com.example.pizzadiromaapp.databinding.FragmentProductsListBinding
import com.example.pizzadiromaapp.presentation.ui.fragments.productslistfragment.adapter.ProductsRecyclerViewAdapter
import com.google.android.material.tabs.TabLayout

class ProductsListFragment : Fragment() {

    private var _binding: FragmentProductsListBinding? = null
    private val binding get() = _binding!!
    var type: String = ""

    //    private val trep = ProductRepositoryImpl(RetrofitClient.pizzaDiRomaApi)
    //    private val getProductsUseCase = GetProductsUseCase(trep)
    private val productsAdapter by lazy { ProductsRecyclerViewAdapter(requireActivity()) }

    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsListBinding.inflate(inflater, container, false)
        binding.productListRecyclerView.adapter = productsAdapter
        //Log.d("myLogs", "onCreateView: $holderType")
        productsAdapter.setOnItemClickListener { productItem ->
            findNavController().navigate(
                ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(
                    productItem
                )
            )
        }

        binding.listSwipeToRefreshLayout.setOnRefreshListener {
            initObserver()
            Log.d("myLogs", "onCreateView:")
        }

        tabListener()

        initObserver()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun tabListener() {
        binding.tabBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> loadProducts(ProductType.ALL.text)
                        1 -> loadProducts(ProductType.PIZZA.text)
                        2 -> loadProducts(ProductType.SUSHI.text)
                        3 -> loadProducts(ProductType.OTHER.text)
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
                    binding.listSwipeToRefreshLayout.isRefreshing = true
                    // show progress bar
                }
                is Resource.Success -> {
                    Log.d("QQQ", "resources: $resources")
                    binding.listSwipeToRefreshLayout.isRefreshing = false
                    (binding.productListRecyclerView.layoutManager as GridLayoutManager).spanCount =
                        if (resources.data.isEmpty()) 1 else 2
                    Log.d("myLogs", "initObserver: ${resources.data}")
                    productsAdapter.mySubmitList(resources.data)
                }
            }
        })
    }

    private fun loadProducts(type: String = "") {
        viewModel.getProducts(type)
        viewModel.tabPosition = binding.tabBar.selectedTabPosition
    }

    private fun checkSelectedTab() {
        if (viewModel.tabPosition != null) {
            binding.tabBar.selectTab(binding.tabBar.getTabAt(viewModel.tabPosition!!))
        } else {
            loadProducts()
        }
    }

    override fun onResume() {
        super.onResume()
        checkSelectedTab()
    }
}