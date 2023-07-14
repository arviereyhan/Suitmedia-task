package com.example.suitmediaproject.ui

import androidx.lifecycle.ViewModel
import com.example.suitmediaproject.data.remote.UserRepository

class ListUserViewModel(private val repository: UserRepository):ViewModel() {
     fun getUser() = repository.getUser()
}