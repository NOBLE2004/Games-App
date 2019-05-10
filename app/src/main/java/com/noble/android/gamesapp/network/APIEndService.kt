package com.noble.android.gamesapp.network

import com.noble.android.gamesapp.model.ResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIEndService {
    @GET("game-library-rest-api/getGamesByMarketAndDevice.json?")
    fun fetchGamesList(
        @Query("jurisdiction") jurisdiction: String,
        @Query("brand") brand: String,
        @Query("deviceGroup") deviceGroup: String,
        @Query("locale") locale: String,
        @Query("currency") currency: String,
        @Query("categories") categories: String,
        @Query("clientId") clientId: String
    ): Single<ResponseData>
}