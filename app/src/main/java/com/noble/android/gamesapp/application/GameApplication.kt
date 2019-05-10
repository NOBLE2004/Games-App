package com.noble.android.gamesapp.application


import com.noble.android.gamesapp.BuildConfig
import com.noble.android.gamesapp.dagger.DaggerAppComponent
import com.noble.android.gamesapp.network.APIEndService
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class GameApplication : DaggerApplication() {

    @Inject
    lateinit var netWorkModule: APIEndService

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        init()
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }

    private fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}