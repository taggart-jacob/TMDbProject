package com.example.tmdbproject.dagger.application

import androidx.lifecycle.MutableLiveData
import com.example.tmdbproject.model.remote.MovieNetworkServiceHelper
import com.example.tmdbproject.model.repository.Repository
import com.example.tmdbproject.model.objects.Result
import com.example.tmdbproject.model.repository.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @AppScope
    fun providesRepository(
        remoteServiceHelper: MovieNetworkServiceHelper,
        liveData: MutableLiveData<List<Result>>
    ): Repository {
        return RepositoryImpl(remoteServiceHelper, liveData)
    }
}