package com.example.pizzadiromaapp.presentation.ui.fragments.productslistfragment.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizzadiromaapp.R
import com.example.pizzadiromaapp.databinding.ItemPlaceHolderBinding
import com.example.pizzadiromaapp.databinding.ProductItemHolderBinding
import com.example.pizzadiromaapp.domain.model.ProductItem
import kotlin.math.roundToInt

class ProductsRecyclerViewAdapter(
    private val activity: Activity
) : ListAdapter<ProductItem, RecyclerView.ViewHolder>(Companion) {

    private val items = mutableListOf<ProductItem>()
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

        private const val PLACE_HOLDER = 0
        private const val ITEM_HOLDER = 1
    }

    override fun getItemCount(): Int {
        return if (items.isNotEmpty()) items.size else 1
    }

    fun mySubmitList(list: List<ProductItem>) {
        items.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PLACE_HOLDER -> {
                ItemPlaceHolder(
                    ItemPlaceHolderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            ITEM_HOLDER -> {
                ProductViewHolder(
                    ProductItemHolderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> error("dashg")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder) {
            when (this) {
                is ItemPlaceHolder -> {
                    binding.placeHolderCardView.layoutParams.width =
                        activity.window.decorView.width - itemView.context.resources.getDimension(
                            R.dimen._20sdp
                        )
                            .roundToInt()
                    binding.titlePlaceHolderTextView.text =
                        activity.resources.getText(R.string.karasik_not_found_text)
                }
                is ProductViewHolder -> {
                    items[position].let { product ->
                        binding.mainCard.layoutParams.width =
                            activity.window.decorView.width / 2 - itemView.context.resources.getDimension(
                                R.dimen._15sdp
                            )
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
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isEmpty()) {
            PLACE_HOLDER
        } else ITEM_HOLDER
    }

    private class ItemPlaceHolder(val binding: ItemPlaceHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class ProductViewHolder(val binding: ProductItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root)
}

