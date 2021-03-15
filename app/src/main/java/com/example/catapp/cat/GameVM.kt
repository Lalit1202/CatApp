package com.example.catapp.cat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.network.CatApi
import com.example.catapp.network.CatProperty
import kotlinx.coroutines.launch
import java.lang.Exception

class CatVM : ViewModel()
{
    private val _properties = MutableLiveData<List<CatProperty>>()

    val properties : LiveData<List<CatProperty>>
        get() = _properties

    private val _response = MutableLiveData<String>()

    val response : LiveData<String>
        get() = _response

    init {
        getCatProperties()
    }

    private fun getCatProperties()
    {

        viewModelScope.launch {
            try {
                var listResult = CatApi.retrofitService.getProperties()
                _properties.value = listResult
            }catch (e: Exception)
            {
                _response.value = "Failure: ${e.message}"

            }
        }




    }
}
