package com.example.catapp.cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.catapp.cat.data.CatProperty
import com.example.catapp.databinding.FragmentCatBinding
import kotlinx.android.synthetic.main.fragment_cat.*

class CatFragment : Fragment() {

    private val viewModel by viewModels<CatVM>()

    var isLastPage : Boolean = false
    var isLoading : Boolean = false



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


        binding.swipeRefresh.setOnRefreshListener {
            viewModel.data.clear()
            viewModel.getCatProperties()
            binding.swipeRefresh.isRefreshing = false
        }





       val layoutManager = binding.photosGrid.layoutManager as GridLayoutManager
        binding.photosGrid.addOnScrollListener(object :PaginationScrollListener(layoutManager){
            override fun isLoading(): Boolean {
                return isLastPage
            }

            override fun isLastPage(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = false

                viewModel.getCatProperties()
            }

        })


        return binding.root

    }




}