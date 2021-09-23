package com.example.pizzadiromaapp.presentation.ui.fragments.productdetailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pizzadiromaapp.R
import com.example.pizzadiromaapp.databinding.FragmentProductDetailsBinding
import com.example.pizzadiromaapp.databinding.FragmentProductsListBinding
import com.example.pizzadiromaapp.domain.model.ProductItem

class ProductDetailsFragment : Fragment() {

    private val args: ProductDetailsFragmentArgs by navArgs()

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        Glide.with(this)
            .load(args.productItem?.imageUrl)
            .centerCrop()
            .into(binding.detailImageView)

        binding.detailPriceTextView.text = buildString {
            append(args.productItem?.price)
            append(" грн")
        }
        binding.detailNameTextView.text = args.productItem?.name
        binding.detailWeightTextView.text = buildString {
            append(args.productItem?.weight)
            append(" г")
        }
        binding.detailDescriptionTextView.text = args.productItem?.description
        binding.detailNameTextView.text = args.productItem?.name.toString()


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}