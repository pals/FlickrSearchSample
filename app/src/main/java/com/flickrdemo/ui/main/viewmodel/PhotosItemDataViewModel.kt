package com.flickrdemo.ui.main.viewmodel

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.flickrdemo.R
import com.flickrdemo.data.model.PhotoItem
import com.flickrdemo.ui.main.interfaces.PhotoGridItemListener

class PhotosItemDataViewModel(
    private val photoGridItemListener: PhotoGridItemListener,
    private val photoItem: PhotoItem
) : BaseObservable() {

    @Bindable
    var photoImage: String = ""

    @Bindable
    var title: String = ""

    init {
        photoImage = photoItem.media.mediaLink
        title = photoItem.title
    }

    companion object {
        @JvmStatic
        @BindingAdapter("photoItemImageUsingGlide")
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
    }

    fun itemClicked() {
        photoGridItemListener.onItemClicked(photoItem)
    }


}