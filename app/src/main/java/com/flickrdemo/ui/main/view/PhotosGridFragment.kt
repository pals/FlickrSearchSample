package com.flickrdemo.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.flickrdemo.R
import com.flickrdemo.data.api.ApiHelper
import com.flickrdemo.data.api.RetrofitBuilder
import com.flickrdemo.data.model.PhotoItem
import com.flickrdemo.database.FlickrDemoDatabase
import com.flickrdemo.databinding.FragmentPhotosGridBinding
import com.flickrdemo.ui.base.Constants
import com.flickrdemo.ui.main.adapter.PhotoGridItemAdapter
import com.flickrdemo.ui.main.interfaces.PhotoGridItemListener
import com.flickrdemo.ui.main.viewmodel.PhotosDataViewModel
import com.flickrdemo.ui.main.viewmodel.RecentSearchViewModel
import com.flickrdemo.ui.main.viewmodel.RecentSearchViewModelFactory
import com.flickrdemo.ui.main.viewmodel.ViewModelFactory
import com.flickrdemo.utils.NetworkUtil
import com.flickrdemo.utils.ResponseHandler
import com.flickrdemo.utils.Status


class PhotosGridFragment : Fragment(),
    PhotoGridItemListener {

    private var binding: FragmentPhotosGridBinding? = null
    private lateinit var searchView: SearchView
    private lateinit var photosDataViewModel: PhotosDataViewModel
    private lateinit var recentSearchViewModel: RecentSearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        initViewModels()
        setupNavigation()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotosGridBinding.inflate(inflater, container, false)
        binding?.viewmodel = photosDataViewModel
//        val view = binding?.viewmodel
        val adapter = PhotoGridItemAdapter(photosDataViewModel)
        binding!!.photosGrid.adapter = adapter

        subscribeUi(adapter)
        return binding!!.root

    }

    private fun initViewModels() {
        val application = requireNotNull(this.activity).application
        val database = FlickrDemoDatabase.getInstance(application).recentSearchDatabaseDao
        val responseHandler = ResponseHandler()
        recentSearchViewModel =
            ViewModelProviders.of(
                requireActivity(),
                RecentSearchViewModelFactory(
                    ApiHelper(RetrofitBuilder.apiService),
                    database,
                    responseHandler
                )
            ).get(RecentSearchViewModel::class.java)

        photosDataViewModel = ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService), database, responseHandler)
        ).get(PhotosDataViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val item = menu.findItem(R.id.action_search)
        searchView = item.actionView as SearchView
        searchMenuSetup()
    }

    private fun searchMenuSetup() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d(Constants.LOGGER_TAG, "Search submitted")
                recentSearchViewModel.saveRecentSearchKeyword(query)
                retrievePhotosList(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })
    }


    private fun retrievePhotosList(tags: String) {

        if (NetworkUtil.isInternetAvailable(requireContext())) {
            getPhotosFromFlickr(tags)
        } else {
            showToastMessage(resources.getString(R.string.err_msg_no_network))
        }

    }

    private fun getPhotosFromFlickr(tags: String) {
        photosDataViewModel.getPhotosList(tags).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding?.spinner?.visibility = View.GONE
                        Log.d(Constants.LOGGER_TAG, "SUCCESS ")
                        resource.data?.let { searchPhotosResponse ->
                            photosDataViewModel.setRetrievedSearchResults(
                                searchPhotosResponse
                            )
                        }
                    }
                    Status.ERROR -> {
                        binding?.spinner?.visibility = View.GONE
                        Log.d(Constants.LOGGER_TAG, "ERROR " + it.message)
                        showToastMessage(it.message)
                    }
                    Status.LOADING -> {
                        binding?.spinner?.visibility = View.VISIBLE
                        Log.d(Constants.LOGGER_TAG, "LOADING ")
                    }
                }
            }
        })
    }

    private fun subscribeUi(adapter: PhotoGridItemAdapter) {

        photosDataViewModel.getSearchedResults().observe(viewLifecycleOwner) { photoItems ->
            adapter.submitList(photoItems)
        }
    }

    override fun onItemClicked(photoItem: PhotoItem) {
        navigateToDetails(photoItem)
    }

    private fun setupNavigation() {
        photosDataViewModel.clickedItem.observe(this, Observer {
            navigateToDetails(it)
        })
    }

    private fun navigateToDetails(photoItem: PhotoItem) {

        Log.d(Constants.LOGGER_TAG, "PhotoItem: " + photoItem.title)
        Navigation.findNavController(binding?.photosGrid!!)
            .navigate(R.id.action_photosGridFragment_to_photoDetailsFragment)

    }

    private fun showToastMessage(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}