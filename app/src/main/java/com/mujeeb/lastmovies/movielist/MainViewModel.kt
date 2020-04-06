package com.mujeeb.lastmovies.movielist

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mujeeb.lastmovies.api.model.Movie
import com.mujeeb.lastmovies.common.FetchDataError
import com.mujeeb.lastmovies.common.NetworkUtils
import com.mujeeb.lastmovies.repository.MoviesRepositoryApi
import kotlinx.coroutines.launch

class MainViewModel(
    private val moviesRepositoryApi: MoviesRepositoryApi,
    private val utils: NetworkUtils
) : ViewModel() {

    private val moviesObservable = MutableLiveData<List<Movie>>()
    private val errorObservable = MutableLiveData<String>()
    private val progressObservable = ObservableBoolean(false)

    private var isLoading: Boolean = false

    fun getMoviesObservable(): LiveData<List<Movie>> = moviesObservable

    fun getErrorObservable() = errorObservable

    fun getProgressObservable() = progressObservable

    fun getData(id: String) {
        viewModelScope.launch {
            if (utils.isOnline()) {
                try {
                    processLoadingStateChange(true)
                    val response = moviesRepositoryApi.fetchMovies(id)
                    handleSuccess(response)
                } catch (error: FetchDataError) {
                    errorObservable.value = error.message
                } finally {
                    processLoadingStateChange(false)
                }
            } else {
                errorObservable.value = "Please check your internet connection and try again!"
            }
        }


    }

    private fun handleSuccess(response: List<Movie>?) {
        moviesObservable.value = response
    }


    private fun processLoadingStateChange(isLoadingInProgress: Boolean) {
        isLoading = isLoadingInProgress

        progressObservable.set(isLoading)

    }

    override fun onCleared() {
        super.onCleared()
        if (isLoading) {
            processLoadingStateChange(false)
        }
    }

}