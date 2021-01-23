package com.lastreact.candyspace.di

import android.app.Application
import com.lastreact.candyspace.di.modules.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): ApplicationComponent
    }

}