package com.example.pexelsvlad.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.pexelsvlad.databinding.CollectionItemBinding
import com.example.pexelsvlad.domain.models.Collection

class CollectionsViewHolder(
    private val binding: CollectionItemBinding,
    private val onUserClicked: (Collection) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(collection: Collection) = with(binding) {

        collectionItemTextView.text=collection.title

        root.setOnClickListener {
            onUserClicked(collection)
        }
    }
}