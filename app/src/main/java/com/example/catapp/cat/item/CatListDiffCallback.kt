package com.example.catapp.cat.item

import androidx.recyclerview.widget.DiffUtil
import com.example.catapp.cat.data.model.CatData

object CatListDiffCallback : DiffUtil.ItemCallback<CatData>() {
    override fun areItemsTheSame(oldItem: CatData, newItem: CatData): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: CatData, newItem: CatData): Boolean {
        return oldItem.url == newItem.url
    }
}