package com.dicoding.timsaw.data.api

import com.dicoding.timsaw.data.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("data.php")
    fun getData(): Call<Data>

}