package com.lastreact.candyspace

import android.app.Application
import com.lastreact.candyspace.di.ApplicationComponent
import com.lastreact.candyspace.di.DaggerApplicationComponent

class App: Application() {
    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}