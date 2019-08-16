package com.example.tmdbproject.model.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdbproject.model.objects.Movies
import com.example.tmdbproject.model.remote.MovieNetworkServiceHelper
import com.example.tmdbproject.model.objects.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryImpl(
    private val movieServiceHelper: MovieNetworkServiceHelper,
    private val moviesLiveData: MutableLiveData<List<Result>>
) : Repository {

    @SuppressLint("CheckResult")
    override fun getPopularMovies(pageNumber: Int) {
        movieServiceHelper.getPopularMovies(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map(Movies::results)
        .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ results -> moviesLiveData.setValue(results)} , Throwable::printStackTrace)

    }

    override fun getPopularMoviesLiveData(): LiveData<List<Result>> {
        return moviesLiveData
    }
}