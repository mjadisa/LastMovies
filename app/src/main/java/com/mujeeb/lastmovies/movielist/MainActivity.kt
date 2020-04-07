package com.mujeeb.lastmovies.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mujeeb.lastmovies.R
import com.mujeeb.lastmovies.data.network.model.Movie
import com.mujeeb.lastmovies.common.LIST_ID
import com.mujeeb.lastmovies.common.MOVIE_INTENT_KEY
import com.mujeeb.lastmovies.databinding.ActivityMainBinding
import com.mujeeb.lastmovies.moviedetails.MovieDetailsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MovieSelectedInterface {
    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.progressVisibility = mainViewModel.getProgressObservable()

        setupRecyclerView()

        mainViewModel.getDataRequestObservable().observe(this, Observer {
            it?.let {
                when (it) {
                    is DataRequestState.Success -> moviesAdapter.setData(it.movies)
                    is DataRequestState.Error -> Toast.makeText(this, it.error, Toast.LENGTH_LONG)
                        .show()

                }
            }
        })


        mainViewModel.getData(LIST_ID)

    }

    private fun setupRecyclerView() {
        rv_movie_list.adapter = moviesAdapter
        rv_movie_list.layoutManager = LinearLayoutManager(this)
    }


    override fun onResultSelected(movie: Movie) {
        val intent = Intent(
            this@MainActivity,
            MovieDetailsActivity::class.java
        )
        intent.putExtra(MOVIE_INTENT_KEY, movie)

        startActivity(intent)
    }
}
