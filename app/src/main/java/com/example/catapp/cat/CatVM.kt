package com.example.catapp.cat

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.catapp.cat.data.CatRepository
import com.example.catapp.cat.item.data.CatProperty
import com.example.freetogame.base.ApiResult
import kotlinx.coroutines.launch

class CatVM : ViewModel() {

    val data = ObservableArrayList<CatProperty>()

    var isRefreshing = ObservableBoolean(false)

    var pageNo = 1

    val repository = CatRepository()

    init {
        fetchCatData()
    }

    fun onRefresh(){
        isRefreshing.set(false)
        pageNo = 1;
        data.clear()
        fetchCatData()
        isRefreshing.set(true)
    }

     fun fetchCatData() {
        viewModelScope.launch {
            try {
                var apiResult = repository.fetchCat(pageNo)
                when (apiResult) {
                    is ApiResult.Success -> {
                        pageNo++
                        Log.e("success","success")
                        data.addAll(apiResult.value)
                    }
                    is ApiResult.Failure -> Log.e("error f","error")
                }
            } catch (e: Exception) {
                Log.e("error",e.localizedMessage?:"eee")
            }
        }
    }
}
