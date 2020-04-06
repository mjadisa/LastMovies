package com.mujeeb.lastmovies.di.module

import com.mujeeb.lastmovies.di.scope.MainActivityScope
import com.mujeeb.lastmovies.movielist.MainActivity
import com.mujeeb.lastmovies.movielist.MovieSelectedInterface
import com.mujeeb.lastmovies.movielist.MoviesAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {
    @Provides
    @MainActivityScope
    fun provideMoviesAdapter(movieSelectedInterface: MovieSelectedInterface): MoviesAdapter {
        return MoviesAdapter(movieSelectedInterface)
    }

    @Provides
    @MainActivityScope
    fun provideMovieSelectedInterface(mainActivity: MainActivity): MovieSelectedInterface {
        return mainActivity
    }
}