package com.flickrdemo.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.flickrdemo.data.model.PhotoItem
import com.flickrdemo.databinding.PhotoGridItemBinding
import com.flickrdemo.ui.main.interfaces.PhotoGridItemListener
import com.flickrdemo.ui.main.viewmodel.PhotosItemDataViewModel

class PhotoGridItemAdapter(var photoGridItemListener: PhotoGridItemListener) :
    ListAdapter<PhotoItem, ViewHolder>(PhotoDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val photoItem: PhotoItem = getItem(position)
        (holder as PhotoItemViewHolder).bind(
            PhotosItemDataViewModel(
                photoGridItemListener,
                photoItem
            )
        )

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PhotoItemViewHolder(
            PhotoGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class PhotoItemViewHolder(
        private val binding: PhotoGridItemBinding
    ) : ViewHolder(binding.root) {

        fun bind(viewModel: PhotosItemDataViewModel) {
            binding.apply {
                binding.viewmodel = viewModel

                executePendingBindings()
            }
        }


    }

}

private class PhotoDiffCallback : DiffUtil.ItemCallback<PhotoItem>() {

    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem.published == newItem.published
    }

    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem == newItem
    }
}