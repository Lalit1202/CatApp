package com.example.catapp.cat.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.cat.data.model.CatData
import com.example.catapp.databinding.GridViewItemBinding

class CatListGridAdaptor :
    ListAdapter<CatData, CatListGridAdaptor.CatDataViewHolder>(CatListDiffCallback) {

    class CatDataViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(catData: CatData) {
            binding.data = catData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatDataViewHolder {
        return CatDataViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CatDataViewHolder, position: Int) {
        val catData = getItem(position)
        holder.bind(catData)
    }

}