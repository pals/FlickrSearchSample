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

    private val _clickedPhotoItemPublishedVal = MutableLiveData<String>()
    val clickedPhotoItemPublishedVal: LiveData<String> = _clickedPhotoItemPublishedVal


    fun getSearchedResults(): MutableLiveData<List<PhotoItem>> {

        return photoItems

    }

    private fun setSearchResults(photoItemsList: List<PhotoItem>) {
        photoItems.value = photoItemsList
    }

    fun getPhotosList(tags: String): LiveData<Resource<PhotosListResponse>> {

        return liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            emit(repository.getPhotosList(tags))
        }

    }

    var clickedItem = MutableLiveData<PhotoItem>()

//    fun getClickedPhotoItem(): PhotoItem? {
//        return clickedItem
//    }

    fun setClickedPhotoItem(photoItem: PhotoItem) {
        clickedItem.value = photoItem
    }

    fun setRetrievedSearchResults(photosListResponse: PhotosListResponse) {

        setSearchResults(photosListResponse.photoItems)
    }

}