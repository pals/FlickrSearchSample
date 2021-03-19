package com.flickrdemo.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.flickrdemo.data.model.PhotoItem
import com.flickrdemo.data.model.PhotosListResponse
import com.flickrdemo.data.repository.Repository
import com.flickrdemo.utils.Resource
import kotlinx.coroutines.Dispatchers

class PhotosDataViewModel(private val repository: Repository) : ViewModel() {

    private val photoItems: MutableLiveData<List<PhotoItem>> = MutableLiveData(arrayListOf())

    fun getSearchedResults(): MutableLiveData<List<PhotoItem>> {

        return photoItems

    }

    private fun setSearchResults(photoItemsList: List<PhotoItem>) {
        photoItems.value = photoItemsList
    }

    fun getPhotosList(tags: String): LiveData<Resource<PhotosListResponse>> {
//        return liveData(Dispatchers.IO) {
//            emit(Resource.loading(data = null))
//            try {
//                emit(Resource.success(data = repository.getPhotosList(tags)))
//            } catch (exception: Exception) {
//                emit(Resource.error(data = null, msg = exception.message ?: "Error occurred!"))
//            }
//        }
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            emit(repository.getPhotosList(tags))
        }

    }

    private var clickedItem: PhotoItem? = null

    fun getClickedPhotoItem(): PhotoItem? {
        return clickedItem
    }

    fun setClickedPhotoItem(photoItem: PhotoItem) {
        clickedItem = photoItem
    }

    fun setRetrievedSearchResults(photosListResponse: PhotosListResponse) {

        setSearchResults(photosListResponse.photoItems)
    }

}