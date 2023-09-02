package com.example.pexelsvlad.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pexelsvlad.databinding.CuratedItemBinding
import com.example.pexelsvlad.domain.models.Photo

class CuratedPhotosAdapter(
    private val onUserClicked: (Photo) -> Unit
) : ListAdapter<Photo, CuratedPhotosViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuratedPhotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CuratedPhotosViewHolder(
            binding = CuratedItemBinding.inflate(layoutInflater, parent, false),
            onUserClicked = onUserClicked
        )
    }

    override fun onBindViewHolder(holder: CuratedPhotosViewHolder, position: Int) {
        val category = currentList[position]
        holder.bind(category)
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }
}