package id.ac.uty.mykade.model

data class Favorite (val id: Long?,
                     val idEvent: String?,
                     val dateEvent: String?,
                     val strHomeTeam: String?,
                     val strAwayTeam: String?,
                     val intHomeScore: String?,
                     val intAwayScore: String?){

    companion object {
        const val TABLE_FAVORIT: String = "TABLE_FAVORIT"
        const val ID: String = "ID_"
        const val FAV_ID_EVENT: String = "FAV_ID_EVENT"
        const val FAV_STR_HOME_TEAM: String = "FAV_STR_HOME_TEAM"
        const val FAV_STR_AWAY_TEAM: String = "FAV_STR_AWAY_TEAM"
        const val FAV_STR_DATE_EVENT: String = "FAV_STR_DATE_EVENT"
        const val FAV_INT_HOME_SCORE: String = "FAV_INT_HOME_SCORE"
        const val FAV_INT_AWAY_SCORE: String = "FAV_STR_AWAY_SCORE"
    }
}
