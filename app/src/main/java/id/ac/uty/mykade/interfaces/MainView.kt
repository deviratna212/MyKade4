package id.ac.uty.mykade.interfaces

import id.ac.uty.mykade.model.Event

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun getTeamList(data: List<Event>)
}