package com.example.suitmediaproject.data.remote.retrofit

import com.example.suitmediaproject.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUser(
        @Query("page") page: Int,
        @Query("per_page") size: Int,
    ): UserResponse
}