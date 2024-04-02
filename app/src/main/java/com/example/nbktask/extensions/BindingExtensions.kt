package com.example.nbktask.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nbktask.R

val DEFAULT_IMAGE_RES = R.drawable.ic_logo_signature
@BindingAdapter("image_url", "default_image", requireAll = false)
fun ImageView.setImageUrl(url: String?, defaultImage: Int?) {
    Glide.with(this).load(url)
        .apply(RequestOptions().placeholder(defaultImage ?: DEFAULT_IMAGE_RES)).into(this)
}

@BindingAdapter("entry_list")
fun <T> submitRecyclerViewList(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is ListAdapter<*, *>) {
        if (data != null)
            (recyclerView.adapter as ListAdapter<T, *>).submitList(data as List<T>)
    }
}