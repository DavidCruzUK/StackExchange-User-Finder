package com.lastreact.stackexchange.di.modules

import com.lastreact.stackexchange.viewmodel.MainViewModel
import com.lastreact.repository.Repository
import com.lastreact.stackexchange.viewmodel.DetailViewModel
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideMainViewModule(repository: Repository) = MainViewModel(repository)

    @Provides
    fun provideDetailViewModule(repository: Repository) = DetailViewModel(repository)

}