package com.noble.android.gamesapp.base

interface APIListener<T> : DataFetchListener<T> {
    fun onError(error: GameSyncError)
}

interface DataFetchListener<T> {
    fun onSuccess(dataModel: T)
}

interface OnClick<T> {
    fun onClick(dataModel: T)
}