package com.example.catapp.cat.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.cat.item.data.CatProperty
import com.example.catapp.databinding.GridViewItemBinding

class PhotoGridAdaptor : ListAdapter<CatProperty, PhotoGridAdaptor.CatPropertyViewHolder>(
    DiffCallback
) {

    class CatPropertyViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(catProperty: CatProperty) {
            binding.data = catProperty
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<CatProperty>() {
        override fun areItemsTheSame(oldItem: CatProperty, newItem: CatProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CatProperty, newItem: CatProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatPropertyViewHolder {
        return CatPropertyViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CatPropertyViewHolder, position: Int) {
        val catProperty = getItem(position)
        holder.bind(catProperty!!)
    }

}