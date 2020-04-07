package com.mujeeb.lastmovies.data.network.repository

import com.mujeeb.lastmovies.data.TMDBApi
import com.mujeeb.lastmovies.data.network.model.Movie
import com.mujeeb.lastmovies.common.API_KEY
import com.mujeeb.lastmovies.common.FetchDataError
import com.mujeeb.lastmovies.common.SORT_BY
import javax.inject.Inject

class MoviesRepositoryApi @Inject constructor(private val api: TMDBApi) {

    suspend fun fetchMovies(listId: String): List<Movie> {
        try {
            return api.getMovies(listId, SORT_BY, API_KEY).results
        } catch (cause: Throwable) {
            throw FetchDataError("Unable to fetch data", cause)
        }
    }


}