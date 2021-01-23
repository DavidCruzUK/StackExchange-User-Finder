package com.lastreact.service.di

import com.lastreact.entity.data.module.response.Items

interface ResponseHandler {

    fun onSuccessResponse(response: List<Items>)

    fun onErrorResponse(error: Throwable)

}