package com.example.pexelsvlad.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pexelsvlad.databinding.CuratedItemBinding
import com.example.pexelsvlad.domain.models.Photo

class CuratedPhotosViewHolder(
    private val binding: CuratedItemBinding,
    private val onUserClicked: (Photo) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo) = with(binding) {
        Glide.with(curatedImageView.context)
            .load(photo.src?.original)
            .into(curatedImageView)

        curatedImageView.setOnClickListener {
            onUserClicked(photo)
        }
    }
}