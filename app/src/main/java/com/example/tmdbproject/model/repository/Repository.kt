package com.example.tmdbproject.model.repository

import androidx.lifecycle.LiveData
import com.example.tmdbproject.model.objects.Result

interface Repository {
    fun getPopularMovies(pageNumber: Int)

    fun getPopularMoviesLiveData(): LiveData<List<Result>>
}