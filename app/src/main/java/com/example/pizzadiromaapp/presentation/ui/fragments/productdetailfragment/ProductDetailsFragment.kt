package com.example.pizzadiromaapp.presentation.ui.fragments.productdetailfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pizzadiromaapp.common.Resource
import com.example.pizzadiromaapp.databinding.FragmentProductDetailsBinding
import com.example.pizzadiromaapp.domain.model.ProductItem
import com.example.pizzadiromaapp.presentation.ui.fragments.productslistfragment.ProductsViewModel

class ProductDetailsFragment : Fragment() {

    private val args: ProductDetailsFragmentArgs by navArgs()

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        initObserver()
        loadProductById()

        binding.detailBackImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.detailSwipeToRefresh.setOnRefreshListener {
            loadProductById()
        }


        return binding.root
    }

    private fun initObserver() {
        viewModel.product.observe(viewLifecycleOwner, { resources ->
            when (resources) {
                is Resource.Failure -> {
                    Log.d("QQQ", "resources: $resources")
                }
                is Resource.Loading -> {
                    Log.d("QQQ", "resources: $resources")
                    binding.detailSwipeToRefresh.isRefreshing = true
                }
                is Resource.Success -> {
                    binding.detailSwipeToRefresh.isRefreshing = false
                    Log.d("QQQ", "initObserver: ${resources.data}")
                    binding.executePendingBindings()
                    binding.invalidateAll()
                }
            }
        })
    }

    private fun loadProductById() {
        viewModel.getProductById(args.productItem.id)
        Log.d("myLogs", "loadProductById: ${args.productItem.id}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}