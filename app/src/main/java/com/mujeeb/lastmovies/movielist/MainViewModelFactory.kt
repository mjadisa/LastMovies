package com.mujeeb.lastmovies.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mujeeb.lastmovies.common.NetworkUtils
import com.mujeeb.lastmovies.data.network.repository.MoviesRepositoryApi


class MainViewModelFactory(
    private val moviesRepositoryApi: MoviesRepositoryApi,
    private val utils: NetworkUtils
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(moviesRepositoryApi, utils) as T
        }
        throw IllegalArgumentException("Please make sure the parameter is of type MainViewModel")
    }

}