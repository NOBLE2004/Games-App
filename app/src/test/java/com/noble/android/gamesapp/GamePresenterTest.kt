package com.noble.android.gamesapp


import com.noble.android.gamesapp.model.GamesData
import com.noble.android.gamesapp.network.APIEndService
import com.noble.android.gamesapp.room.AppDataBase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit


@RunWith(JUnit4::class)
class GamePresenterTest {


    @get:Rule
    var mockitoRule = MockitoJUnit.rule()
    @Mock
    lateinit var dataBase: AppDataBase
    @Mock
    lateinit var apiEndService: APIEndService

    @Mock
    lateinit var view: GameContract.View


    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testSortData() {
        val presenter= GamePresenter(view,dataBase,apiEndService)
        val testData = getTestData()
        val sortData = presenter.sortGamesList(testData)
        Assert.assertEquals("a",sortData[0].gameName)
    }

    @Test
    fun testSortWithSameLetterData() {
        val presenter= GamePresenter(view,dataBase,apiEndService)
        val testData = getTestSameLetterData()
        val sortData = presenter.sortGamesList(testData)
        Assert.assertEquals("a",sortData[0].gameName)
    }

    private fun getTestSameLetterData(): List<GamesData> {
        val albumDataList = ArrayList<GamesData>()
        albumDataList.add(GamesData("aa", "Test", "Test"))
        albumDataList.add(GamesData("a", "Test", "Test"))
        albumDataList.add(GamesData("pa", "Test", "Test"))
        albumDataList.add(GamesData("aaa", "Test", "Test"))
        albumDataList.add(GamesData("aa", "Test", "Test"))
        return albumDataList
    }

    private fun getTestData(): List<GamesData> {
        val albumDataList = ArrayList<GamesData>()
        albumDataList.add(GamesData("z", "Test", "Test"))
        albumDataList.add(GamesData("s", "Test", "Test"))
        albumDataList.add(GamesData("p", "Test", "Test"))
        albumDataList.add(GamesData("a", "Test", "Test"))
        albumDataList.add(GamesData("b", "Test", "Test"))
        return albumDataList
    }}