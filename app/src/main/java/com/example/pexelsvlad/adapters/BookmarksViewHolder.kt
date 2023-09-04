package com.example.pexelsvlad.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pexelsvlad.databinding.BookmarksItemBinding
import com.example.pexelsvlad.domain.models.Photo

class BookmarksViewHolder(
    private val binding: BookmarksItemBinding,
    private val onUserClicked: (Photo) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    fun bind(photo: Photo) = with(binding) {
        Glide.with(bookmarksImageView.context)
            .load(photo.src?.original)
            .into(bookmarksImageView)

        authorName.text=photo.photographer

        bookmarksImageView.setOnClickListener {
            onUserClicked(photo)
        }
    }

}