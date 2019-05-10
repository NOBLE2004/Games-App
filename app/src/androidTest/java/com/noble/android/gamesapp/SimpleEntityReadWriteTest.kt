package com.noble.android.gamesapp

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.noble.android.gamesapp.application.GameApplication
import com.noble.android.gamesapp.model.GamesData
import com.noble.android.gamesapp.room.AppDataBase
import com.noble.android.gamesapp.room.GameDao
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var gameDao: GameDao
    private lateinit var db: AppDataBase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext().applicationContext as GameApplication
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        gameDao = db.gamesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testDBInstance() {
        Assert.assertNotNull(db)
    }

    @Test
    @Throws(Exception::class)
    fun testDAOInstance() {
        Assert.assertNotNull(gameDao)
    }

    @Test
    @Throws(Exception::class)
    fun dbWriteTest() {
        val gameData = GamesData("Title", "gameId1", "Test")
        db.gamesDao().insert(gameData)
        val data = db.gamesDao().getAll()
        data.subscribe { it ->
            assertEquals(true, !it.isEmpty())
        }
    }

    @Test
    @Throws(Exception::class)
    fun dbFetchTest() {
        val gameData = GamesData("Title", "gameId1", "Test")
        db.gamesDao().insert(gameData)
        val data = db.gamesDao().getAll()
        data.subscribe { it ->
            val game = it[0]
            assertEquals("Title", game.gameName)
        }
    }
}