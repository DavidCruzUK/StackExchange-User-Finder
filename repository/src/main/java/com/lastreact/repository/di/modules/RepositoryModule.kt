package com.lastreact.repository.di.modules

import android.app.Application
import android.content.Context
import com.lastreact.repository.Repository
import com.lastreact.repository.db.UserDao
import com.lastreact.repository.db.UsersDatabase
import com.lastreact.service.ServiceApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(serviceApi: ServiceApi, db: UserDao): Repository = Repository(serviceApi, db)

    @Singleton
    @Provides
    fun provideUsersDatabase(app: Application) = UsersDatabase.getInstance(app).userDao()
}