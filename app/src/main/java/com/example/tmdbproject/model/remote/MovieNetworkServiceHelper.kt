package com.example.tmdbproject.model.remote

import com.example.tmdbproject.model.objects.Movies
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

//"db637646bffa9037e9af87ff3295ec21"

class MovieNetworkServiceHelper {
    @Inject
    fun MovieNetworkServiceHelper(){}

    private fun getRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val myInterceptor = MyInterceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(myInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getPopularMovies(pageNumber: Int): Single<Movies> {
        val retrofit = getRetrofit()
        val service = retrofit.create(MovieNetworkService::class.java)
        return service.getPopularMovies(pageNumber)
    }

    companion object MyInterceptor: Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response{
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("language", "en")
                .addQueryParameter("sort_by", "popularity.desc")
                .addQueryParameter("api_key", "db637646bffa9037e9af87ff3295ec21")
                .build()
            val request = original.newBuilder().url(url).build()
            return chain.proceed(request)
        }

    }
}