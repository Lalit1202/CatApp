package com.example.catapp.cat.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.cat.data.model.CatData
import com.example.catapp.databinding.GridViewItemBinding

class CatListGridAdaptor(val listener: OnItemClickListener) :
    ListAdapter<CatData, CatListGridAdaptor.CatDataViewHolder>(CatListDiffCallback) {


    class CatDataViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(catData: CatData,listener: OnItemClickListener) {
            binding.data = catData
            binding.executePendingBindings()
           itemView.setOnClickListener {

               listener.onItemClicked(catData,itemView)
           }

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
        holder.bind(catData,listener)

    }

}
interface OnItemClickListener{
    fun onItemClicked(catdata: CatData,itemView: View)
}