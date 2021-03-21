package com.flickrdemo.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.flickrdemo.data.model.PhotoItem
import com.flickrdemo.databinding.PhotoGridItemBinding
import com.flickrdemo.ui.main.viewmodel.PhotosDataViewModel

class PhotoGridItemAdapter(private val viewModel: PhotosDataViewModel) :
    ListAdapter<PhotoItem, ViewHolder>(PhotoDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val item = getItem(position)
        (holder as PhotoItemViewHolder).bind(viewModel, item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PhotoItemViewHolder(
            PhotoGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class PhotoItemViewHolder(
        private val binding: PhotoGridItemBinding
    ) : ViewHolder(binding.root) {

        fun bind(viewModel: PhotosDataViewModel, item: PhotoItem) {
            binding.apply {
                binding.viewmodel = viewModel
                binding.photoItem = item
                binding.executePendingBindings()
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