package com.example.catapp.cat.data

import com.example.catapp.cat.data.model.CatData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("v1/images/search?limit=20&mime_types=png")
    suspend fun getCats(@Query("page") pageNo: Int): Response<ArrayList<CatData>>
}