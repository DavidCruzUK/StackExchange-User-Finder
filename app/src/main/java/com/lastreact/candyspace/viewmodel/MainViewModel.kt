package com.lastreact.candyspace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastreact.repository.Repository
import com.lastreact.service.di.ResponseHandler
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    suspend fun getUsers(responseHandler: ResponseHandler) {
        viewModelScope.launch {
            repository.getUsers(responseHandler)
        }
    }
}