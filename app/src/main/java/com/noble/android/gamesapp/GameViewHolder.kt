package com.noble.android.gamesapp

import android.view.View
import com.noble.android.gamesapp.base.OnClick
import com.noble.android.gamesapp.model.GamesData

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_games.view.*

class GameViewHolder<T>(val view: View) : BaseViewHolder<GamesData>(view) {
    override fun onBindData(dataModel: GamesData, onClickListener: OnClick<GamesData>) {
        itemView.txtGameName.text = dataModel.gameName
        Picasso.get().load(dataModel.imagePath).into(itemView.imgLogo);
        itemView.setOnClickListener { onClickListener.onClick(dataModel) }
    }
}