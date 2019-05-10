package com.noble.android.gamesapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.noble.android.gamesapp.base.OnClick
import com.noble.android.gamesapp.model.GamesData


class GameAdapter : RecyclerView.Adapter<GameViewHolder<GamesData>> {
    private var gamesList: List<GamesData>
    private var onClick: OnClick<GamesData>

    constructor(gamesList: List<GamesData>, onClick: OnClick<GamesData>) : super() {
        this.gamesList = gamesList
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): GameViewHolder<GamesData> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_games,
            parent, false)
        return GameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    override fun onBindViewHolder(viewHolder: GameViewHolder<GamesData>, pos: Int) {
        viewHolder.onBindData(gamesList[pos], onClick)
    }

}