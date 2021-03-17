package com.flickrdemo.data.model

import com.google.gson.annotations.SerializedName

data class PhotosListResponse(
    val description: String,
    val generator: String,

    @SerializedName("items")
    val photoItems: List<PhotoItem>,
    val link: String,
    val modified: String,
    val title: String
)