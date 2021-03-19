package com.flickrdemo.data.repository

import com.flickrdemo.data.api.ApiHelper
import com.flickrdemo.data.model.PhotosListResponse
import com.flickrdemo.database.RecentSearchDatabaseDao
import com.flickrdemo.database.RecentSearchWord
import com.flickrdemo.utils.Resource
import com.flickrdemo.utils.ResponseHandler

class Repository(
    private val apiHelper: ApiHelper,
    private val database: RecentSearchDatabaseDao,
    private val responseHandler: ResponseHandler
) {

    //    suspend fun getPhotosList(tags: String) = apiHelper.getPhotosList(tags)
    suspend fun getPhotosList(tags: String): Resource<PhotosListResponse> {
        return try {
            val response = apiHelper.getPhotosList(tags)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun insertRecentSearchKeyword(recentSearchWord: RecentSearchWord) =
        database.insert(recentSearchWord)

}