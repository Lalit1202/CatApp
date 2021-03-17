package com.example.catapp.cat

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.cat.data.CatProperty
import com.example.catapp.cat.data.CatRepository
import com.example.freetogame.base.ApiResult
import kotlinx.coroutines.launch

class CatVM : ViewModel() {

    val data = ObservableArrayList<CatProperty>()

    var pageNo = 1

    val repository = CatRepository()

    init {
        getCatProperties()
    }

    private fun getCatProperties() {
        viewModelScope.launch {
            try {
                var apiResult = repository.fetchCat(pageNo)
                when (apiResult) {
                    is ApiResult.Success -> {
                        pageNo++
                        Log.e("success","success")
                        data.addAll(apiResult.value)
                    }
                    is ApiResult.Failure -> Log.e("error","error")
                }
            } catch (e: Exception) {
                Log.e("error","error")
            }
        }
    }
}
