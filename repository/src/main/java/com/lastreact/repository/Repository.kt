package com.lastreact.repository

import com.lastreact.entity.data.module.response.StackApiResponse
import com.lastreact.repository.data.mapper.mapToPresent
import com.lastreact.repository.data.mapper.mapToUserDao
import com.lastreact.repository.db.User
import com.lastreact.repository.db.UserDao
import com.lastreact.service.ServiceApi
import com.lastreact.service.di.ResponseHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(
    private val response: ServiceApi,
    private val db: UserDao
) {

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getUsers(handler: ResponseHandler) {
        disposable.add(
            response.requestServiceUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res -> onSuccessResponse(handler, res) },
                    { err -> handler.onErrorResponse(err) })
        )
    }

    private fun onSuccessResponse(handler: ResponseHandler, res: StackApiResponse) {
        CoroutineScope(Dispatchers.Default).launch {
            val usersDao: List<User> = res.mapToUserDao()
            db.insertListOfUsers(usersDao)
            val users = db.getAllUsersList().mapToPresent()
            handler.onSuccessResponse(users)
        }
    }

}