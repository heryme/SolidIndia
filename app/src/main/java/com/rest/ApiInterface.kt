package com.rest

import com.model.ProductResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("product/data")
    fun productList(@Header("language") language: String): Call<ProductResponse>

}