package com.noble.android.gamesapp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GamesData(


    @SerializedName("gameName")
    @ColumnInfo(name = "gameName") val gameName: String,

    @SerializedName("gameId")
    @PrimaryKey
    @ColumnInfo(name = "gameId") val GameId:  String,

    @SerializedName("imageUrl")
    @ColumnInfo(name = "imageUrl") val imagePath: String?
)