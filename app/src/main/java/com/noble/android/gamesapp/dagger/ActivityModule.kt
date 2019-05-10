package com.noble.android.gamesapp.dagger


import com.noble.android.gamesapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class, FragmentProvider::class))
    internal abstract fun bindMainActivity(): MainActivity
}