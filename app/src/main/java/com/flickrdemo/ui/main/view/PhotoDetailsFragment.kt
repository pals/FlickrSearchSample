package com.flickrdemo.ui.main.view

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.flickrdemo.R
import com.flickrdemo.data.api.ApiHelper
import com.flickrdemo.data.api.RetrofitBuilder
import com.flickrdemo.data.model.PhotoItem
import com.flickrdemo.database.FlickrDemoDatabase
import com.flickrdemo.databinding.FragmentPhotoDetailsBinding
import com.flickrdemo.ui.base.Constants
import com.flickrdemo.ui.main.viewmodel.PhotosDataViewModel
import com.flickrdemo.ui.main.viewmodel.ViewModelFactory


class PhotoDetailsFragment : Fragment() {

    private var binding: FragmentPhotoDetailsBinding? = null

    private lateinit var photosDataViewModel: PhotosDataViewModel
    var photoItem: PhotoItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = requireActivity().application
        val database = FlickrDemoDatabase.getInstance(application).recentSearchDatabaseDao
        photosDataViewModel = ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService), database)
        ).get(PhotosDataViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        photoItem = photosDataViewModel.getClickedPhotoItem()
        binding!!.viewmodel = photoItem
        setupUI(this)
        return binding!!.root
    }

    companion object {

        @RequiresApi(Build.VERSION_CODES.N)
        private fun setupUI(photoDetailsFragment: PhotoDetailsFragment) {
            val uri: String? =
                Uri.parse(photoDetailsFragment.photoItem?.media?.mediaLink).toString()

            Log.d(Constants.LOGGER_TAG, "uri: " + uri)
            Glide.with(photoDetailsFragment.binding?.ivPhoto?.context!!)
                .load(uri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                ).into(photoDetailsFragment.binding?.ivPhoto!!)

            var strDescription = ""

            if (photoDetailsFragment.photoItem?.description != null) {

                strDescription = photoDetailsFragment.photoItem?.description?.trim()!!
            }

            var str: String = Html.fromHtml(strDescription, Html.FROM_HTML_MODE_LEGACY).toString()
            str = "Photo Description$str"
            photoDetailsFragment.binding?.tvIgDescription?.text =
                str
        }
    }

}