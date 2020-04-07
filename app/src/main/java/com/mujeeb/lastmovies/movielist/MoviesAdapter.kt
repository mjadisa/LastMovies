package com.mujeeb.lastmovies.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mujeeb.lastmovies.R
import com.mujeeb.lastmovies.data.network.model.Movie
import com.mujeeb.lastmovies.common.IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*


class MoviesAdapter(listener: MovieSelectedInterface) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var movies: List<Movie>

    companion object {
        private var listener: MovieSelectedInterface? = null
    }

    init {
        movies = ArrayList()
        MoviesAdapter.listener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun setData(movies: List<Movie>?) {
        if (movies != null) {
            this.movies = movies
        }
        notifyDataSetChanged()
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.tv_title.text = movie.title
            itemView.tv_overview.text = movie.overview
            if (movie.poster_path.isNotEmpty()) {
                Picasso.get()
                    .load(IMAGE_BASE_URL + movie.poster_path)
                    .error(R.mipmap.ic_launcher_round)
                    .resize(400, 380)
                    .into(itemView.iv_movie_art)
            } else {
                Picasso.get().load(R.mipmap.ic_launcher_round).into(itemView.iv_movie_art)
            }

            itemView.card_view.setOnClickListener { handleMovieSelected(movie) }
        }

        private fun handleMovieSelected(movie: Movie) {
            listener?.onResultSelected(movie)
        }

    }


}