package com.mujeeb.lastmovies.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreatedBy(
    @SerializedName("gravatar_hash") val gravatar_hash: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("id") val id: String
) : Parcelable