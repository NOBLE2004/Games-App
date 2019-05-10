package com.noble.android.gamesapp.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.noble.android.gamesapp.model.GamesData
import io.reactivex.Maybe

@Dao
interface GameDao {
    @Query("select * from gamesdata")
    fun getAll(): Maybe<List<GamesData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg gamesdata: GamesData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(gamesdata: List<GamesData>)
}