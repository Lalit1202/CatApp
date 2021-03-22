package com.example.catapp.cat.vm

import android.util.Log
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.cat.data.CatRepository
import com.example.catapp.cat.data.model.CatData
import com.example.catapp.cat.view.CatFragment
import com.example.freetogame.base.ApiResult
import kotlinx.android.synthetic.main.fragment_cat.*
import kotlinx.coroutines.launch

class CatVM : ViewModel() {

    val data = ObservableArrayList<CatData>()

    var isRefreshing = ObservableBoolean(false)
    var isLoadingMore =ObservableBoolean(false)
    val catFragment = CatFragment()


    var pageNo = 1

    val repository = CatRepository()

    init {
        fetchCatData()
    }

    fun onRefresh() {
        isRefreshing.set(false)
        resetPageNumber()
        fetchCatData(true)
    }

    fun fetchCatData(refresh: Boolean = false) {
        viewModelScope.launch {


            var apiResult = repository.fetchCat(pageNo)
            when (apiResult) {
                is ApiResult.Success -> onCatApiSuccess(refresh, apiResult.value)
                is ApiResult.Failure -> Log.w("error f", "error") // can show error page
            }

            onRefreshDone()
        }
    }

     private fun onCatApiSuccess(refresh: Boolean, listCat: List<CatData>) {
        updatePageNumberByOne()
        if (refresh) resetDataSet()
        addToCatData(listCat)
    }

     fun addToCatData(listCat: List<CatData>) {
        data.addAll(listCat)
    }

     fun updatePageNumberByOne() {
        pageNo++
    }

     fun resetPageNumber() {
        pageNo = 1
    }

     fun resetDataSet() {
        data.clear()
    }

    private fun onRefreshDone() {
        isRefreshing.set(true)
    }
    fun onLoad()
    {
        isLoadingMore.set(true)

    }
}
