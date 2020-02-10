package id.ac.uty.mykade.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.ac.uty.mykade.model.FavTeam
import id.ac.uty.mykade.model.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(context: Context)
    : ManagedSQLiteOpenHelper(context, "FavoriteTeam.db", null, 1) {

    companion object {
        private var instances: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context):MyDatabaseOpenHelper{
            if (instances == null){
                instances= MyDatabaseOpenHelper(context.applicationContext)
            }
            return instances as MyDatabaseOpenHelper
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorite.TABLE_FAVORIT, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.FAV_ID_EVENT to TEXT + UNIQUE,
            Favorite.FAV_STR_DATE_EVENT to TEXT,
            Favorite.FAV_STR_HOME_TEAM to TEXT,
            Favorite.FAV_STR_AWAY_TEAM to TEXT,
            Favorite.FAV_INT_HOME_SCORE to TEXT,
            Favorite.FAV_INT_AWAY_SCORE to TEXT)

        db.createTable(
            FavTeam.TB_FAV_TEAM, true,
            FavTeam.ID to INTEGER + PRIMARY_KEY+ AUTOINCREMENT,
            FavTeam.ID_TEAM to TEXT+ UNIQUE,
            FavTeam.TEAM to TEXT,
            FavTeam.BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORIT, true)
        db.dropTable(FavTeam.TB_FAV_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)