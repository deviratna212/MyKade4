package id.ac.uty.mykade.interfaces

import id.ac.uty.mykade.model.Event
import id.ac.uty.mykade.model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun getEventDetail(data: List<Event>)
    fun getHomeLogo(data: List<Team>)
    fun getAwayLogo(data: List<Team>)
}