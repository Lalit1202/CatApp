package com.example.catapp.cat.data

import com.example.catapp.cat.item.data.CatProperty
import com.example.freetogame.base.ApiClient
import com.example.freetogame.base.ApiResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatRepository {

    private val api = ApiClient.getClient().create(CatApi::class.java)

    suspend fun fetchCat(
        pageNo : Int
    ): ApiResult<List<CatProperty>, String> {

        lateinit var result: ApiResult<List<CatProperty>, Nothing>

        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            result = ApiResult.Failure(throwable.localizedMessage ?: "IO Error")
        }

        withContext(Dispatchers.IO + exceptionHandler) {
            val response = api.getCats(pageNo)

            if (response.isSuccessful) {
                result = ApiResult.Success(response.body()!!)
            } else {
                result = ApiResult.Failure("error fetching data")
            }
        }
        return result
    }
}