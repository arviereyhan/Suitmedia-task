package com.example.suitmediaproject

import android.content.Context
import com.example.suitmediaproject.data.remote.UserRepository
import com.example.suitmediaproject.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}