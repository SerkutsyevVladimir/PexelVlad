package com.example.pexelsvlad.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pexelsvlad.databinding.BookmarksItemBinding
import com.example.pexelsvlad.domain.models.Photo

class BookmarksAdapter(
    private val onUserClicked: (Photo) -> Unit
) : ListAdapter<Photo, BookmarksViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BookmarksViewHolder(
            binding = BookmarksItemBinding.inflate(layoutInflater, parent, false),
            onUserClicked = onUserClicked
        )
    }

    override fun onBindViewHolder(holder: BookmarksViewHolder, position: Int) {
        val bookmark = currentList[position]
        holder.bind(bookmark)
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