package com.mujeeb.lastmovies.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TMDBResponse(

    @SerializedName("iso_639_1") val iso_639_1: String,
    @SerializedName("id") val id: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("iso_3166_1") val iso_3166_1: String,
    @SerializedName("total_results") val total_results: Int,
    @SerializedName("revenue") val revenue: Long,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("name") val name: String,
    @SerializedName("public") val public: Boolean,
    @SerializedName("sort_by") val sort_by: String,
    @SerializedName("description") val description: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("average_rating") val average_rating: Double,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("created_by") val created_by: CreatedBy,
    @SerializedName("poster_path") val poster_path: String
) : Parcelable