package com.mujeeb.lastmovies.moviedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mujeeb.lastmovies.R
import com.mujeeb.lastmovies.data.network.model.Movie
import com.mujeeb.lastmovies.common.DateUtils.Companion.changeDateFormat
import com.mujeeb.lastmovies.common.IMAGE_BASE_URL
import com.mujeeb.lastmovies.common.MOVIE_INTENT_KEY
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tb_movie_details_screen.setNavigationOnClickListener { finish() }

        val movie: Movie? = intent.getParcelableExtra(MOVIE_INTENT_KEY)
        if (movie != null) {
            tv_movie_title.text =
                String.format("%s %s", getString(R.string.movie_title_label), movie.title)
            tv_movie_overview.text = String.format(
                "%s ", movie.overview
            )
            tv_adult.text = String.format(
                "%s %s",
                getString(R.string.movie_adult_label),
                movie.adult
            )
            tv_rate.text = String.format(
                "%s %s",
                getString(R.string.movie_rate_label),
                movie.popularity
            )
            tv_vote.text = String.format(
                "%s %s",
                getString(R.string.movie_vote_label),
                movie.vote_count
            )

            tv_release_date.text = String.format(
                "%s %s", getString(R.string.movie_release_date_label), changeDateFormat(
                    movie.release_date
                )
            )

            if (movie.poster_path.isNotEmpty()) {
                Picasso.get()
                    .load(IMAGE_BASE_URL + movie.poster_path)
                    .resize(700, 580)
                    .error(R.mipmap.ic_launcher_round)
                    .into(iv_movie_art)
            } else {
                Picasso.get().load(R.mipmap.ic_launcher_round).into(iv_movie_art)
            }


        }

    }


}
