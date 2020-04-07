package com.mujeeb.lastmovies.movielist

import androidx.databinding.ObservableBoolean
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

    private val dataRequestObservable: MutableLiveData<DataRequestState> = MutableLiveData()

    private val progressObservable = ObservableBoolean(false)

    private var isLoading: Boolean = false

    fun getDataRequestObservable() = dataRequestObservable

    fun getProgressObservable() = progressObservable

    fun getData(id: String) {
        viewModelScope.launch {
            if (utils.isOnline()) {
                try {
                    processLoadingStateChange(true)
                    val response = moviesRepositoryApi.fetchMovies(id)
                    handleSuccess(response)
                } catch (error: FetchDataError) {
                    dataRequestObservable.value = DataRequestState.Error(error.message)
                } finally {
                    processLoadingStateChange(false)
                }
            } else {
                dataRequestObservable.value =
                    DataRequestState.Error("Please check your internet connection and try again!")
            }
        }


    }

    private fun handleSuccess(response: List<Movie>?) {
        dataRequestObservable.value = DataRequestState.Success(response)
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