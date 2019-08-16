package com.example.tmdbproject.dagger.activity

import com.example.tmdbproject.view.activity.MainActivity
import com.example.tmdbproject.viewmodel.MainViewModelFactory
import dagger.Subcomponent
@ActScope
@Subcomponent(modules = [ActModule::class])
    interface ActComponent {

        val mainViewModelFactory: MainViewModelFactory

        fun inject(mainActivity: MainActivity)
}