package com.lastreact.service.di

import com.lastreact.entity.data.module.response.StackApiResponse

interface ResponseHandler {

    fun onSuccessResponse(response: StackApiResponse)

    fun onErrorResponse(error: Throwable)

}