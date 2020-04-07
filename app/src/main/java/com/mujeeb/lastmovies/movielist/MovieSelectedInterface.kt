package com.mujeeb.lastmovies.movielist

import com.mujeeb.lastmovies.data.network.model.Movie

interface MovieSelectedInterface {
    fun onResultSelected(movie: Movie)
}