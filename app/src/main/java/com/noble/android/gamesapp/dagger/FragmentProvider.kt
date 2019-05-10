package com.noble.android.gamesapp.dagger



import com.noble.android.gamesapp.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment
}