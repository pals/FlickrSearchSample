package com.flickrdemo.ui.main.interfaces

import com.flickrdemo.data.model.PhotoItem

interface PhotoGridItemListener {
    fun onItemClicked(photoItem: PhotoItem)
}