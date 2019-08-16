package com.example.tmdbproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdbproject.R
import com.example.tmdbproject.databinding.FragmentItemBinding
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ItemFragment : BottomSheetDialogFragment() {

    private var binding: FragmentItemBinding? = null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.setOnShowListener{ dialog ->
            val d = dialog as BottomSheetDialog
            val coordinatorLayout = d.findViewById<CoordinatorLayout>(R.id.coordinatorLayout)
            val bottomSheetInternal = d.findViewById<LinearLayout>(R.id.movieInfo)
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetInternal)
            BottomSheetBehavior.from(coordinatorLayout?.parent as View).peekHeight = bottomSheetInternal!!.height
            bottomSheetBehavior.peekHeight = bottomSheetInternal.height
            coordinatorLayout.parent?.requestLayout()
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item, container, false)
        return binding?.root
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bundle = arguments
        binding?.movieTitle?.text = bundle?.getString("movieTitle")
        binding?.movieReleaseDate?.text = String.format(
            context!!.getString(R.string.release_date),
            bundle?.getString("movieReleaseDate")
        )
        binding?.movieOverview?.text = bundle?.getString("movieOverview")
        val progressDrawable = CircularProgressDrawable(context!!)
        progressDrawable.strokeWidth = 5f
        progressDrawable.centerRadius = 30f
        progressDrawable.start()
        val options = RequestOptions()
        options.placeholder(progressDrawable)
        options.centerCrop()
        Glide.with(activity!!)
            .asBitmap()
            .load("http://image.tmdb.org/t/p/w780" + bundle?.getString("movieBackDropPath"))
            .apply(options)
            .into(binding!!.movieImage)
    }

    companion object {

        fun getInstance(
            movieTitle: String,
            movieReleaseDate: String, movieOverview: String, movieBackDropPath: String
        ): ItemFragment {
            val fragment = ItemFragment()
            val bundle = Bundle()
            bundle.putString("movieTitle", movieTitle)
            bundle.putString("movieReleaseDate", movieReleaseDate)
            bundle.putString("movieOverview", movieOverview)
            bundle.putString("movieBackDropPath", movieBackDropPath)
            fragment.arguments = bundle
            return fragment
        }
    }
}
