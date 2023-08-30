package com.example.sampleproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.sampleproject.databinding.ItemLayoutBinding

class ItemsAdapter(private val itemClickInterface: ItemClickInterface) : ListAdapter<Post, ItemsViewHolder>(ItemsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickInterface
        )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}