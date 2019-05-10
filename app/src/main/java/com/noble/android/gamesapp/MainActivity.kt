package com.noble.android.gamesapp

import android.os.Bundle
import com.noble.android.gamesapp.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init() {
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, HomeFragment::class.simpleName).commit()

    }
}
