package com.lastreact.stackexchange.di.modules

import com.lastreact.stackexchange.viewmodel.MainViewModel
import com.lastreact.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideMainViewModule(repository: Repository) = MainViewModel(repository)

}