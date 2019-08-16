package com.example.tmdbproject.dagger.application

import com.example.tmdbproject.dagger.activity.ActComponent
import com.example.tmdbproject.dagger.activity.ActModule
import com.example.tmdbproject.model.remote.MovieNetworkServiceHelper
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, LiveDataModule::class])
interface AppComponent {

    fun newActivityComponent(activityModule: ActModule): ActComponent

    fun getMovieServiceHelper(): MovieNetworkServiceHelper
}