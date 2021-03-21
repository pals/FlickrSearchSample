package com.flickrdemo.ui.main.adapter

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.flickrdemo.R
import com.flickrdemo.data.model.PhotoItem

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<PhotoItem>?) {

    items?.let {
        (listView.adapter as PhotoGridItemAdapter).submitList(items)
    }
}

@BindingAdapter("setPhotoItemImageUsingGlide")
fun loadImage(view: ImageView, photoImage: String?) {
    val uri: String? = Uri.parse(photoImage).toString()
    Glide.with(view.context)
        .load(uri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(view)

}