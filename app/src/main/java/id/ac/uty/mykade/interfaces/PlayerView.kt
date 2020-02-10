package id.ac.uty.mykade.interfaces

import id.ac.uty.mykade.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun getPlayers(data: List<Player>)

}