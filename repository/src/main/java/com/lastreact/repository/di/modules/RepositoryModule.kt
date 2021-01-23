package com.lastreact.repository.di.modules

import com.lastreact.repository.Repository
import com.lastreact.service.ServiceApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(serviceApi: ServiceApi): Repository = Repository(serviceApi)
}