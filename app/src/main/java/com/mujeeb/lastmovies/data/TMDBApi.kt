package com.mujeeb.lastmovies.data


import com.mujeeb.lastmovies.data.network.model.TMDBResponse
import com.mujeeb.lastmovies.common.BASE_URL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBApi {
    @GET(BASE_URL + "list/{list_id}")
    suspend fun getMovies(
        @Path("list_id") list_id: String,
        @Query("sort_by") sort_by: String,
        @Query("api_key") api_key: String
    ): TMDBResponse
}