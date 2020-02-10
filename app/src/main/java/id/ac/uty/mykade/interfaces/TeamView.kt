package id.ac.uty.mykade.interfaces

import id.ac.uty.mykade.model.League
import id.ac.uty.mykade.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun getListLeague(data: List<League>)
    fun getTeams(teams: List<Team>)
}