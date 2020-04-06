package com.mujeeb.lastmovies.di.module

import com.mujeeb.lastmovies.di.scope.MainActivityScope
import com.mujeeb.lastmovies.movielist.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildMainActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class, AdapterModule::class])
    @MainActivityScope
    abstract fun mainActivity(): MainActivity


}