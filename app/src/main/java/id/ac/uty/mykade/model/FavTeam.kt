package id.ac.uty.mykade.model

data class FavTeam(val id:Long?,
                   val idTeam: String?,
                   val strTeam: String?,
                   val strTeamBadge: String?){
    companion object {
        const val TB_FAV_TEAM: String = "TABLE_FAVE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "idTeam"
        const val TEAM: String = "strTeam"
        const val BADGE: String = "strTeamBadge"
    }
}