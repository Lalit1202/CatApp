package com.example.catapp.cat.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager

import com.example.catapp.cat.item.CatListGridAdaptor
import com.example.catapp.cat.vm.CatVM
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.util.PaginationScrollListener
import com.example.catapp.util.hasNetwork

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
        binding.photosGrid.adapter = CatListGridAdaptor()

        val layoutManger = binding.photosGrid.layoutManager as GridLayoutManager
        //var progressBar = binding.pgbar
       var textView =  binding.tv
        if(!hasNetwork(this.requireContext()))
        {
          textView.visibility = View.VISIBLE
          binding.photosGrid.visibility = View.GONE
            binding.swipeRefresh.visibility = View.GONE


        }
        else {


            binding.photosGrid.addOnScrollListener(object : PaginationScrollListener(layoutManger) {
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {

                    isLoading = false
                    viewModel.fetchCatData()

                }

            })
        }





        return binding.root

    }

}

