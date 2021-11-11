package com.example.pizzadiromaapp.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @BindingAdapter("image")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .centerCrop()
            .into(view)
    }
}