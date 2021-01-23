package com.lastreact.repository

import com.lastreact.service.ServiceApi
import com.lastreact.service.di.ResponseHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class Repository(private val response: ServiceApi) {

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getUsers(handler: ResponseHandler) {
        disposable.add(
            response.requestServiceUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { res ->
                        handler.onSuccessResponse(res)
                    },
                    { err ->
                        handler.onErrorResponse(err)
                    }
                )
        )
    }

}