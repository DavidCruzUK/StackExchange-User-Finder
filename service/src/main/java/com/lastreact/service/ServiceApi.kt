package com.lastreact.service

import com.lastreact.entity.data.module.response.StackApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ServiceApi {

    @GET("users?order=desc&sort=name&site=stackoverflow")
    fun requestServiceUsers(): Single<StackApiResponse>

}