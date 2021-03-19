package com.flickrdemo.data.api

import com.flickrdemo.data.model.PhotosListResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("servicesx/feeds/photos_public.gne?nojsoncallback=1&tagmode=any&format=json")
    suspend fun getPhotosList(
        @Query("tags") tags: String
    ): PhotosListResponse

}