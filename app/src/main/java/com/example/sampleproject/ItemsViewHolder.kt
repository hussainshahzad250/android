package com.example.sampleproject

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sampleproject.databinding.ItemLayoutBinding

class ItemsViewHolder(
    private val binding: ItemLayoutBinding, private val itemClickInterface: ItemClickInterface
) : ViewHolder(binding.root) {

    fun bind(item: Post) {
        binding.itemName.text = item.title

        binding.itemName.setOnClickListener {
            Toast.makeText(binding.root.context, "Item name called", Toast.LENGTH_SHORT).show()
            itemClickInterface.onClick(item.title ?: "HELLO HELLO")
        }

    }

}