package com.mujeeb.lastmovies.di.module;

import android.app.Application;

import com.mujeeb.lastmovies.data.TMDBApi;
import com.mujeeb.lastmovies.di.scope.ApplicationScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mujeeb.lastmovies.common.ConstantsKt.BASE_URL;
import static com.mujeeb.lastmovies.common.ConstantsKt.CACHE_SIZE;
import static com.mujeeb.lastmovies.common.ConstantsKt.TIMEOUT_REQUEST;


@Module
public class NetworkModule {

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }


    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .cache(cache)
                .build();


    }

    @Provides
    @ApplicationScope
    Cache provideCache(Application application) {
        return new Cache(application.getCacheDir(), CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    TMDBApi provideService(Retrofit retrofit) {
        return retrofit.create(TMDBApi.class);

    }


}
