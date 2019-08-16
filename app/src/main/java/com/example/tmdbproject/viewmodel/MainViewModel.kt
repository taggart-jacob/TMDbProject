package com.example.tmdbproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tmdbproject.model.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

    val popularMoviesLiveData
        get() = repository.getPopularMoviesLiveData()

    fun getPopularMovies(pageNumber: Int) {
        repository.getPopularMovies(pageNumber)
    }
}