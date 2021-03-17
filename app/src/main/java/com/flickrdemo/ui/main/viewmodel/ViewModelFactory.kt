package com.flickrdemo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flickrdemo.data.api.ApiHelper
import com.flickrdemo.data.repository.Repository
import com.flickrdemo.database.RecentSearchDatabaseDao

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val database: RecentSearchDatabaseDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(PhotosDataViewModel::class.java)) {

            return PhotosDataViewModel(Repository(apiHelper, database)) as T

        }
        throw IllegalArgumentException("Unknown class name")
    }

}