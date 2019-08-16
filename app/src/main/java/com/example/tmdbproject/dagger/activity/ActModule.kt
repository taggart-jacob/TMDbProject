package com.example.tmdbproject.dagger.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbproject.R
import com.example.tmdbproject.databinding.ActivityMainBinding
import com.example.tmdbproject.view.adapter.MoviesAdapter
import com.example.tmdbproject.viewmodel.MainViewModel
import com.example.tmdbproject.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import java.util.ArrayList

@Module
class ActModule(private val activity: AppCompatActivity) {

    @Provides
    @ActScope
    fun providesMainViewModel(factory: MainViewModelFactory): MainViewModel {
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

    @Provides
    @ActScope
    fun providesActivityMainBinding(): ActivityMainBinding {
        return DataBindingUtil.setContentView(activity, R.layout.activity_main)
    }

    @Provides
    fun providesGridLayoutManager(): GridLayoutManager {
        return GridLayoutManager(activity, 2)
    }

    @Provides
    fun providesDividerItemDecoration(): DividerItemDecoration {
        return DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
    }

    @Provides
    fun providesPopularMoviesAdapter(): MoviesAdapter {
        return MoviesAdapter(ArrayList(), activity as MoviesAdapter.MyClickListener)
    }
}