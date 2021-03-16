package com.example.catapp.cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.catapp.cat.item.PhotoGridAdaptor
import com.example.catapp.databinding.FragmentCatBinding

class CatFragment : Fragment() {

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
        binding.photosGrid.adapter =
            PhotoGridAdaptor()
        return binding.root

    }


}