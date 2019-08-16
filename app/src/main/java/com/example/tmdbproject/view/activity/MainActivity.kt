package com.example.tmdbproject.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbproject.AppController
import com.example.tmdbproject.dagger.activity.ActModule
import com.example.tmdbproject.databinding.ActivityMainBinding
import com.example.tmdbproject.util.EndlessRecyclerScroll
import com.example.tmdbproject.view.adapter.MoviesAdapter
import com.example.tmdbproject.viewmodel.MainViewModel
import javax.inject.Inject
import com.example.tmdbproject.model.objects.Result
import com.example.tmdbproject.view.fragment.ItemFragment


private const val BOTTOM_SHEET = "bottomSheet"
class MainActivity : AppCompatActivity(), MoviesAdapter.MyClickListener {

    private var scrollListener: EndlessRecyclerScroll? = null

    @Inject
    internal val binding: ActivityMainBinding? = null

    @Inject
    internal var mainViewModel: MainViewModel? = null

    @Inject
    internal var layoutManager: GridLayoutManager? = null

    @Inject
    internal var dividerItemDecoration: DividerItemDecoration? = null

    @Inject
    internal var adapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (getApplication() as AppController).getApplicationComponent()
            ?.newActivityComponent(ActModule(this))?.inject(this)
        configureRecyclerview()
        mainViewModel?.getPopularMovies(1)
        mainViewModel?.popularMoviesLiveData?.observe(this, { popularMovies ->
            adapter?.addMoviesToAdapter(popularMovies)
            adapter?.notifyDataSetChanged()
        })
    }

    override fun itemClicked(result: Result) {
        val fragment = ItemFragment.getInstance(
            result.title,
            result.release_date, result.overview,
            result.backdrop_path
        )
        fragment.show(supportFragmentManager, BOTTOM_SHEET)
    }

    private fun configureRecyclerview() {
        scrollListener = object : EndlessRecyclerScroll(layoutManager!!) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                mainViewModel!!.getPopularMovies(page)
            }
        }
        binding?.recyclerView?.setLayoutManager(layoutManager)
        binding?.recyclerView?.addItemDecoration(dividerItemDecoration!!)
        binding?.recyclerView?.addOnScrollListener(scrollListener!!)
        binding?.recyclerView?.setAdapter(adapter)
    }
}