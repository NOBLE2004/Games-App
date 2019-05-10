package com.noble.android.gamesapp.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.noble.android.gamesapp.model.GamesData

@Database(entities = arrayOf(GamesData::class), version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun gamesDao(): GameDao
}