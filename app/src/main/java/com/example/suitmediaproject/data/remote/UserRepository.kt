package com.example.suitmediaproject.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.suitmediaproject.data.remote.response.UserResponse
import com.example.suitmediaproject.data.remote.retrofit.ApiService
import com.example.suitmediaproject.Result

class UserRepository(private val apiService: ApiService) {
    fun getUser(): LiveData<Result<UserResponse>> = liveData {
        try {
            val response = apiService.getUser(page = 1, size = 12)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.toString()))
        }
    }
}