package com.flickrdemo.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getPhotosList(tags: String) = apiService.getPhotosList(tags)
}