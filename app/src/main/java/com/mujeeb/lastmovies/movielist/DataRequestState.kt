package com.mujeeb.lastmovies.movielist

import com.mujeeb.lastmovies.data.network.model.Movie

sealed class DataRequestState{
    class Success(val movies : List<Movie>?) : DataRequestState()
    class Error(val error: String?): DataRequestState()
}