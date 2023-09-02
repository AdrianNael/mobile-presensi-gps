package com.example.mahapanca.api

import com.example.mahapanca.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("nik") nik: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}