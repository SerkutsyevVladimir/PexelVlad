package com.example.pexelsvlad.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pexelsvlad.databinding.CollectionItemBinding
import com.example.pexelsvlad.domain.models.Collection

class CollectionsAdapter (
    private val onUserClicked: (Collection) -> Unit
) : ListAdapter<Collection, CollectionsViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CollectionsViewHolder(
            binding = CollectionItemBinding.inflate(layoutInflater, parent, false),
            onUserClicked = onUserClicked
        )
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        val collection = currentList[position]
        holder.bind(collection)
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Collection>() {
            override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
                return oldItem == newItem
            }
        }
    }
}