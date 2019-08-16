package com.example.tmdbproject.model.remote

import com.example.tmdbproject.model.objects.Movies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieNetworkService {
    @GET("3/discover/movie")
    fun getPopularMovies(@Query("page") page: Int): Single<Movies>
}