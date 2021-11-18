package com.ankur.crypto

import com.ankur.crypto.data.CryptoResponseData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api/v1/assets")
    fun retrieveCryptoResp(): Call<CryptoResponseData>
}