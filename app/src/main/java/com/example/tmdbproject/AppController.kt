package com.example.tmdbproject

import android.app.Application
import com.example.tmdbproject.dagger.application.AppComponent
import com.example.tmdbproject.dagger.application.DaggerApplicationComponent

class AppController : Application() {

    private var applicationComponent: AppComponent? = null

    fun getApplicationComponent(): AppComponent? {
        if (applicationComponent == null) {
            applicationComponent = createApplicationComponent()
        }
        return applicationComponent
    }

    private fun createApplicationComponent(): AppComponent {
        return DaggerApplicationComponent.create()
    }
}