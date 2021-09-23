package com.example.pizzadiromaapp.presentation.ui.fragments.productslistfragment.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizzadiromaapp.R
import com.example.pizzadiromaapp.databinding.ProductItemHolderBinding
import com.example.pizzadiromaapp.domain.model.ProductItem
import kotlin.math.roundToInt

class ProductsRecyclerViewAdapter(
    private val activity: Activity
) : ListAdapter<ProductItem, RecyclerView.ViewHolder>(Companion) {

    private var onItemClickListener: ((ProductItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (ProductItem) -> Unit) {
        onItemClickListener = listener
    }

    companion object : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ProductViewHolder(
            ProductItemHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as ProductViewHolder) {
            getItem(position).let { product ->
                binding.mainCard.layoutParams.width =
                    activity.window.decorView.width / 2 - itemView.context.resources.getDimension(R.dimen._15sdp)
                        .roundToInt()

                binding.mainCard.setOnClickListener {
                    onItemClickListener?.invoke(product)
                }

                Glide.with(holder.itemView.context)
                    .load(product.imageUrl)
                    .centerCrop()
                    .into(binding.holderImageView)

                binding.productNameTextView.text = product.name
                binding.productWeightTextView.text = buildString {
                    append(product.weight)
                    append(" г")
                }
                binding.productPriceTextView.text = product.price.toString()
            }
        }
    }

    private class ProductViewHolder(val binding: ProductItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root)
}