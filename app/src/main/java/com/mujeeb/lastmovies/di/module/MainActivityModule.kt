package com.mujeeb.lastmovies.di.module

import androidx.lifecycle.ViewModelProviders
import com.mujeeb.lastmovies.common.NetworkUtils
import com.mujeeb.lastmovies.di.scope.MainActivityScope
import com.mujeeb.lastmovies.movielist.MainActivity
import com.mujeeb.lastmovies.movielist.MainViewModel
import com.mujeeb.lastmovies.movielist.MainViewModelFactory
import com.mujeeb.lastmovies.repository.MoviesRepositoryApi
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    @MainActivityScope
    fun provideMainViewModelFactory(
        moviesRepositoryApi: MoviesRepositoryApi, networkUtils: NetworkUtils
    ) = MainViewModelFactory(moviesRepositoryApi, networkUtils)

    @Provides
    @MainActivityScope
    fun provideMainViewModel(
        mainActivity: MainActivity,
        mainViewModelFactory: MainViewModelFactory
    ): MainViewModel {
        return ViewModelProviders.of(mainActivity, mainViewModelFactory)
            .get(MainViewModel::class.java)
    }
}