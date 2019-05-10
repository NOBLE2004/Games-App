package com.noble.android.gamesapp.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ResponseData(
    @SerializedName("games")
    @Expose
    val result: Map<String, GamesData>? = null
)