package com.example.catapp

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catapp.cat.PhotoGridAdaptor
import com.example.catapp.network.CatProperty


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CatProperty>?)
{
    val adapter = recyclerView.adapter as PhotoGridAdaptor
    adapter.submitList(data)

}

@BindingAdapter("ImageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?)
{
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)

    }
}