package com.noble.android.gamesapp

import com.noble.android.gamesapp.base.*
import com.noble.android.gamesapp.constants.AppConstants
import com.noble.android.gamesapp.model.GamesData
import com.noble.android.gamesapp.model.ResponseData
import com.noble.android.gamesapp.network.APIEndService
import com.noble.android.gamesapp.room.AppDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface GameContract {
    interface View : BaseContract.View {
        fun updateGames(GamesData: List<GamesData>)
    }

    interface Presenter : BaseContract.Presenter {
        fun fetchGamesList()
        fun sortGamesList(GamesData: List<GamesData>): List<GamesData>
    }

    interface Interactor : BaseContract.Interactor {
        fun fetchGamesList(listener: APIListener<List<GamesData>>)
        fun fetchGamesFromDatabase(listener: DataFetchListener<List<GamesData>>)
    }
}

class GamePresenter(val view: GameContract.View, roomDatabase: AppDataBase, apiService: APIEndService) :
    GameContract.Presenter {
    /**
     * sortedGamesList method - will sort games list based on games title
     */
    override fun sortGamesList(GamesData: List<GamesData>): List<GamesData> {
        return GamesData.sortedBy { it -> it.gameName }

    }

    private val gamesInteractor = GamesInteractor(roomDatabase, apiService)
    /**
     * fetchGamesList method, will fetch games details from remote server and store
     * into local storage and give final sorted list into view
     */
    override fun fetchGamesList() {
        view.showProgress()
        gamesInteractor.fetchGamesFromDatabase(object : DataFetchListener<List<GamesData>> {
            override fun onSuccess(dataModel: List<GamesData>) {
                if (!dataModel.isEmpty()) {
                    view.hideProgress()
                    view.updateGames(sortGamesList(dataModel))
                }
            }
        })
        gamesInteractor.fetchGamesList(object : APIListener<List<GamesData>> {
            override fun onSuccess(dataModel: List<GamesData>) {
                view.hideProgress()
                view.updateGames(sortGamesList(dataModel))
            }

            override fun onError(error: GameSyncError) {
                view.hideProgress()
                view.showError(error)
            }
        })
    }

    override fun onDestroy() {
        gamesInteractor.clear()
    }
}


class GamesInteractor(val dataBase: AppDataBase, apiService: APIEndService) : BaseInteractor(apiService),
    GameContract.Interactor {
    /**
     * fetchGamesFromDatabase method will fetch games data from local storage
     */
    override fun fetchGamesFromDatabase(listener: DataFetchListener<List<GamesData>>) {
        val gamesRequest = dataBase.gamesDao().getAll().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
        gamesRequest.doOnSubscribe { compositeDisposable.add(it) }
            .subscribe { listener.onSuccess(it) }
    }

    /**
     * fetchGamesList method will fetch games data from server, and store into local storage
     */
    override fun fetchGamesList(listener: APIListener<List<GamesData>>) {
        compositeDisposable.add(
            apiService.fetchGamesList(
                AppConstants.BASE_JURISDICTION,
                AppConstants.BASE_BRAND
                , AppConstants.BASE_DEVICE
                , AppConstants.BASE_LOCALE, AppConstants.BASE_CURRENCY
                , AppConstants.BASE_CATEGORIES
                , AppConstants.BASE_CLIENTID
            )
                .doOnSuccess { dataBase.gamesDao().insertAll(extractGamesList(it)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listener.onSuccess(extractGamesList(it)) },
                    { listener.onError(GameSyncError(it, "", AppConstants.SERVER_ERROR)) })
        )

    }

    private fun extractGamesList(it: ResponseData?): List<GamesData> {
        val gameDataList = ArrayList<GamesData>()
        val keys = it?.result?.keys
        val dataMap = it?.result
        keys?.forEach {
            gameDataList.add(dataMap?.get(it)!!)
        }
        return gameDataList
    }

    override fun clear() {
        compositeDisposable.clear()
    }

}