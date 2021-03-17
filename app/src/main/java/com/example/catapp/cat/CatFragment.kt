package com.example.catapp.cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catapp.cat.item.PhotoGridAdaptor
import com.example.catapp.databinding.FragmentCatBinding

class CatFragment : Fragment() {

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    private val viewModel by viewModels<CatVM>()

    companion object {
        @JvmStatic
        fun newInstance() = CatFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCatBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.photosGrid.adapter = PhotoGridAdaptor()
        val layoutManger = binding.photosGrid.layoutManager as GridLayoutManager

        binding.photosGrid.addOnScrollListener(object : PaginationScrollListener(layoutManger){
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
               isLoading = false

                viewModel.getCatProperties()
            }

        })

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.data.clear()
            viewModel.getCatProperties()
            binding.swipeRefresh.isRefreshing = false
        }


        return binding.root

    }


}