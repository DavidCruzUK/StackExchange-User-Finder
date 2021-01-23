package com.lastreact.candyspace.di.modules

import com.lastreact.candyspace.viewmodel.MainViewModel
import com.lastreact.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideMainViewModule(repository: Repository) = MainViewModel(repository)
}