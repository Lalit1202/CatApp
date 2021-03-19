package com.example.catapp.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapp.cat.item.CatListGridAdaptor
import com.example.catapp.cat.data.model.CatData

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: ArrayList<CatData>?) {
    val adapter = recyclerView.adapter as CatListGridAdaptor
    adapter.submitList(data!!.toList())
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .thumbnail(0.1f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgView)
    }
}

@BindingAdapter("app:refreshing")
fun bindSwipeRefreshing(swipeRefresh : androidx.swiperefreshlayout.widget.SwipeRefreshLayout, refreshing : Boolean){
    swipeRefresh.isRefreshing = false
}
