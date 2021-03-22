package com.example.freetogame.base

import com.example.catapp.MyApp
import com.example.catapp.util.hasNetwork
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.thecatapi.com/"

object ApiClient {

    val context = MyApp.instance
    private lateinit var apiClient: Retrofit

    val cacheSize = (5 * 1024 * 1024).toLong()
    val myCache = Cache(context.cacheDir, cacheSize)

    fun getClient(): Retrofit {
        if (!this::apiClient.isInitialized) {
            apiClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkhttpClient())
                .build()
        }

        return apiClient
    }

    private fun getOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    val okHttpClient = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(context))
                request.newBuilder().header("Cache-Control", "public, max-age=" + 30).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build() // 7days
            chain.proceed(request)
        }
        .build()


}