package com.noble.android.gamesapp.dagger

import android.app.Application
import com.noble.android.gamesapp.application.GameApplication
import com.noble.android.gamesapp.network.NetWorkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        NetWorkModule::class
    )
)
@Singleton
interface AppComponent : AndroidInjector<GameApplication> {

    fun injectApp(application: GameApplication)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }


}



