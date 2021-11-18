package com.ankur.crypto

import com.ankur.crypto.data.CryptoResponseData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    private val service: ApiInterface

    companion object {
        //1
        const val BASE_URL = "https://data.messari.io/"
    }



    init {
        // 2
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val interceptor =
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        val client =  OkHttpClient.Builder().addInterceptor(interceptor).build();
        val retrofit = Retrofit.Builder()
            // 1
            .baseUrl(BASE_URL).client(client)
            //3
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //4
        service = retrofit.create(ApiInterface::class.java)
    }

    fun getRepositories(callback: Callback<CryptoResponseData>) { //5
        val call = service.retrieveCryptoResp()
        call.enqueue(callback)
    }
}