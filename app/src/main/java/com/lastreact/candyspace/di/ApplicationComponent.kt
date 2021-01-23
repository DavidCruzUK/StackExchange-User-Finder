package com.lastreact.candyspace.di

import android.app.Application
import com.lastreact.candyspace.ui.activities.MainActivity
import com.lastreact.candyspace.di.modules.ApplicationModule
import com.lastreact.repository.di.modules.RepositoryModule
import com.lastreact.service.di.modules.ServiceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    ServiceModule::class,
    RepositoryModule::class
])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): ApplicationComponent
    }

}