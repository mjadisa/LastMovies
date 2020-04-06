package com.mujeeb.lastmovies.movielist

import com.mujeeb.lastmovies.api.model.Movie

interface MovieSelectedInterface {
    fun onResultSelected(movie: Movie)
}