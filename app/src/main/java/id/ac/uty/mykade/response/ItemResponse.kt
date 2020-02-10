package id.ac.uty.mykade.response

import id.ac.uty.mykade.model.Event
import id.ac.uty.mykade.model.Team

data class ItemResponse (
    var events: List<Event>,
    var teams: List<Team>
)