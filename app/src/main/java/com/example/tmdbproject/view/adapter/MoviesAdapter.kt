package com.example.tmdbproject.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdbproject.R
import com.example.tmdbproject.databinding.MovieItemBinding
import com.example.tmdbproject.model.objects.Result

class MoviesAdapter(private val popularMovies: MutableList<Result>, private val clickListener: MyClickListener) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return popularMovies.size
    }


    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): MoviesAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.movie_item, viewGroup, false
        ) as MovieItemBinding
        return ViewHolder(binding)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(@NonNull holder: MoviesAdapter.ViewHolder, i: Int) {
        val temp = popularMovies[i]
        holder.itemView.setOnClickListener{ view -> clickListener.itemClicked(popularMovies[i]) }
        val progressDrawable = CircularProgressDrawable(holder.itemView.getContext())
        progressDrawable.setStrokeWidth(5f)
        progressDrawable.setCenterRadius(30f)
        progressDrawable.start()
        val options = RequestOptions()
        options.centerCrop()
        options.placeholder(progressDrawable)
        Glide.with(holder.itemView.getContext())
            .asBitmap()
            .load("http://image.tmdb.org/t/p/w342" + temp.poster_path)
            .apply(options)
            .into(holder.binding.movieImage)
    }

    fun addMoviesToAdapter(newResults: List<Result>) {
        popularMovies.addAll(newResults)
    }

    inner class ViewHolder(@param:NonNull internal var binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.getRoot())

    interface MyClickListener {
        fun itemClicked(result: Result)
    }
}