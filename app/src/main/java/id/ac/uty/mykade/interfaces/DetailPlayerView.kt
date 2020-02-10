package id.ac.uty.mykade.interfaces

import id.ac.uty.mykade.model.Player

interface DetailPlayerView {
    fun showLoading()
    fun hideLoading()
    fun getPlayerDetail(data: List<Player>)
}