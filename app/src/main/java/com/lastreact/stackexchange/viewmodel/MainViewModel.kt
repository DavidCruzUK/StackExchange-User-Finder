package com.lastreact.stackexchange.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.repository.Repository
import com.lastreact.repository.data.mapper.mapToPresent
import com.lastreact.service.di.ResponseHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    var userItems: ArrayList<UserItem> = ArrayList()
    var userItemsFiltered: ArrayList<UserItem> = ArrayList()

    sealed class UiModel {
        data class ShowProgress(val show: Boolean): UiModel()
    }

    private val _model: MutableStateFlow<UiModel> = MutableStateFlow(UiModel.ShowProgress(false))
    val model: StateFlow<UiModel> get() = _model

    fun getUsers(responseHandler: ResponseHandler) {
        viewModelScope.launch {
            repository.getUsers(responseHandler)
        }
    }

    suspend fun getUsers(): List<UserItem> {
        return repository.getUsersList()
    }

    fun getFilteredItems(text: String): List<UserItem> {
        userItemsFiltered = ArrayList(userItems)
        return userItemsFiltered
            .filter { it.displayName?.contains(text, true) ?: false }
            .sortedBy { it.displayName }
    }

    fun showProgressBar(show: Boolean) {
        _model.value = UiModel.ShowProgress(show)
    }
}