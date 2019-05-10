package com.noble.android.gamesapp.base


import com.noble.android.gamesapp.network.APIEndService
import io.reactivex.disposables.CompositeDisposable

interface BaseContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError(error: GameSyncError)
    }

    interface Presenter {
        fun onDestroy()
    }

    interface Interactor {
        fun clear()
    }
}

open class BaseInteractor(apiEndService: APIEndService) : BaseContract.Interactor {
    protected val apiService: APIEndService = apiEndService

    protected val compositeDisposable = CompositeDisposable()
    override fun clear() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }
    }

}
