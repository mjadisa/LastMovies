package com.mujeeb.lastmovies.movielist

import com.mujeeb.lastmovies.api.model.Movie

sealed class DataRequestState{
    class Success(val movies : List<Movie>?) : DataRequestState()
    class Error(val error: String?): DataRequestState()
}