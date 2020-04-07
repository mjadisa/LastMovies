package com.mujeeb.lastmovies.di.component

import android.app.Application
import com.mujeeb.lastmovies.common.TMDBApp
import com.mujeeb.lastmovies.di.module.BuildMainActivityModule
import com.mujeeb.lastmovies.di.module.NetworkModule
import com.mujeeb.lastmovies.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule


@Component(
    modules = [AndroidInjectionModule::class, BuildMainActivityModule::class, NetworkModule::class]
)
@ApplicationScope
interface AppComponent {
    fun inject(app: TMDBApp)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}