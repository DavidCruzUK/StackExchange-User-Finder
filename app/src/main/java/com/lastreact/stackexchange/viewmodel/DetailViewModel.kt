package com.lastreact.stackexchange.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private val _model: MutableStateFlow<DetailModel> =
        MutableStateFlow(DetailModel.RetrieveUserById(user = UserItem(userId = 0)))
    val model: StateFlow<DetailModel> get() = _model

    sealed class DetailModel {
        data class RetrieveUserById(val user: UserItem) : DetailModel()
    }

    suspend fun retrieveUserById(id: Int) {
        viewModelScope.launch {
            val user = repository.getUserById(id)
            _model.value = DetailModel.RetrieveUserById(user)
        }
    }

}