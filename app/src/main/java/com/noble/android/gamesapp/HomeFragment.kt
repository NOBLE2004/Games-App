package com.noble.android.gamesapp

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.android.gamesapp.network.APIEndService
import com.noble.android.gamesapp.base.GameSyncError
import com.noble.android.gamesapp.base.BaseFragment
import com.noble.android.gamesapp.base.OnClick
import com.noble.android.gamesapp.model.GamesData
import com.noble.android.gamesapp.room.AppDataBase
import com.noble.android.gamesapp.utils.Utils
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), GameContract.View {
    override fun updateGames(GamesData: List<GamesData>) {
        albumList.addAll(GamesData)
        albumAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progressView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
    }

    override fun showError(error: GameSyncError) {
        Utils.showError(activity!!, getString(R.string.server_error))
    }

    @Inject
    lateinit var apiEndService: APIEndService
    private var presenter: GamePresenter? = null
    @Inject
    lateinit var database: AppDataBase
    private val albumList = ArrayList<GamesData>()
    private val albumAdapter = GameAdapter(albumList, object : OnClick<GamesData> {
        override fun onClick(dataModel: GamesData) {
            //Have to take action
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = GamePresenter(this, database, apiEndService)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter?.fetchGamesList()
       rvAlbum.layoutManager = GridLayoutManager(activity,2)
        rvAlbum.adapter = albumAdapter
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}