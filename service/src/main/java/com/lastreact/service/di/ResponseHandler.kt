package com.lastreact.service.di

import com.lastreact.entity.data.module.response.UserItem

interface ResponseHandler {

    fun onSuccessResponse(response: List<UserItem>)

    fun onErrorResponse(error: Throwable)

}