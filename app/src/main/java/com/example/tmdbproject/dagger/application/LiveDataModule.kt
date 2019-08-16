package com.example.tmdbproject.dagger.application

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import com.example.tmdbproject.model.objects.Result

@Module
class LiveDataModule {

    @Provides
    fun providesResultListMutableLiveData(): MutableLiveData<List<Result>> {
        return MutableLiveData()
    }
}