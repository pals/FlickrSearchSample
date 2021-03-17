package com.flickrdemo.data.repository

import com.flickrdemo.data.api.ApiHelper
import com.flickrdemo.database.RecentSearchDatabaseDao
import com.flickrdemo.database.RecentSearchWord

class Repository(private val apiHelper: ApiHelper, private val database: RecentSearchDatabaseDao) {

    suspend fun getPhotosList(tags: String) = apiHelper.getPhotosList(tags)

    suspend fun insertRecentSearchKeyword(recentSearchWord: RecentSearchWord) =
        database.insert(recentSearchWord)

}