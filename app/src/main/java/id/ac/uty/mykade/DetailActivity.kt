package id.ac.uty.mykade

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.google.gson.Gson
import id.ac.uty.mykade.model.Favorite
import id.ac.uty.mykade.database.database
import id.ac.uty.mykade.interfaces.DetailView
import id.ac.uty.mykade.model.Event
import id.ac.uty.mykade.model.Team
import id.ac.uty.mykade.network.ApiRepository
import id.ac.uty.mykade.presenters.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.text.DateFormat
import java.text.SimpleDateFormat

class DetailActivity : AppCompatActivity(), DetailView{

    private var dataTeamAway: MutableList<Team> = mutableListOf()
    private var dataTeamHome: MutableList<Team> = mutableListOf()
    private var item: MutableList<Event> = mutableListOf()
    private lateinit var presenter: DetailPresenter

    private var menuItem:Menu? = null
    private lateinit var idNya : String
    private var isFavorite: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        idNya = intent.getStringExtra("item")

        favoriteState()
        initData()
        presenter.getDetailEvent(idNya)

    }

    override fun getEventDetail(data: List<Event>) {
        progress_bar_detail.invisible()
        item.clear()
        item.addAll(data)
        presenter.getLogoHome(item?.get(0).idHomeTeam)
        presenter.getLogoAway(item?.get(0).idAwayTeam)


        showData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem=menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite)
                    removeFavorite()
                else
                    addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use{
                insert(
                    Favorite.TABLE_FAVORIT,
                    Favorite.FAV_ID_EVENT to item?.get(0).idEvent,
                    Favorite.FAV_STR_DATE_EVENT to item?.get(0).dateEvent,
                    Favorite.FAV_STR_HOME_TEAM to item?.get(0).strHomeTeam,
                    Favorite.FAV_STR_AWAY_TEAM to item?.get(0).strAwayTeam,
                    Favorite.FAV_INT_HOME_SCORE to item?.get(0).intHomeScore,
                    Favorite.FAV_INT_AWAY_SCORE to item?.get(0).intAwayScore)
            }
            snackbar(swipeDetail, "Event added to favorite!")
        } catch (e: SQLiteConstraintException){
            Log.d("lololo detail db", "${e.localizedMessage}")
        }
    }

    private fun removeFavorite(){
        try {
            database.use{
                delete(Favorite.TABLE_FAVORIT, "(FAV_ID_EVENT = {id})", "id" to idNya)
            }
            snackbar(swipeDetail, "Event removed from favorite!")
        } catch (e: SQLiteConstraintException){
            Log.d("lololo detail db", "${e.localizedMessage}")
        }
    }

    private fun setFavorite(){
        if (isFavorite)
            menuItem?.getItem(0)?.icon= ContextCompat.getDrawable(this, R.drawable.ic_added_favorit)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_favorit)
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORIT)
                .whereArgs("(FAV_ID_EVENT = {idEvent})", "idEvent" to idNya)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty())
                isFavorite = true
        }
    }

    override fun showLoading() {
        progress_bar_detail.visible()
    }

    override fun hideLoading() {
        progress_bar_detail.invisible()
    }

    override fun getHomeLogo(data: List<Team>) {
        progress_bar_detail.invisible()
        dataTeamHome.clear()
        dataTeamHome.addAll(data)
        Glide.with(this).load(dataTeamHome.get(0).strTeamBadge).into(imageHome)
    }

    override fun getAwayLogo(data: List<Team>) {
        progress_bar_detail.invisible()
        dataTeamAway.clear()
        dataTeamAway.addAll(data)
        Glide.with(this).load(dataTeamAway.get(0).strTeamBadge).into(image_away)
    }

    fun initData(){
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)


    }

    fun showData() {
        val displayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val size = displayMetrics.widthPixels/5
        val params = RelativeLayout.LayoutParams(size, size)

        imageHome.layoutParams = params
        image_away.layoutParams = params

        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val newDateFormat: DateFormat = SimpleDateFormat("E, dd MMM yyyy")

        val date = dateFormat.parse(item?.get(0).dateEvent)

        tvDate.text = newDateFormat.format(date)

        tvTeamHome.text = item?.get(0).strHomeTeam
        tvTeamAway.text = item?.get(0).strAwayTeam

        tvShotHome.text =""
        tvStrAway.text =""

        tvScoreHome.text = item?.get(0).intHomeScore
        tvScoreAway.text = item?.get(0).intAwayScore

        tvGolHome.text = item?.get(0).strHomeGoalDetails
        tvGolAway.text = item?.get(0).strAwayGoalDetails

        tvShotHome.text = item?.get(0).intHomeShots
        tvshotsaway.text = item?.get(0).intAwayShots

        tvKeeperHome.text = item?.get(0).strHomeLineupGoalkeeper
        tvKeeperAway.text = item?.get(0).strAwayLineupGoalkeeper

        tvDefHome.text = item?.get(0).strHomeLineupDefense
        tvDefAway.text = item?.get(0).strAwayLineupDefense

        tvMidHome.text = item?.get(0).strHomeLineupMidfield
        tvMidAway.text = item?.get(0).strAwayLineupMidfield

        tvForHome.text = item?.get(0).strHomeLineupForward
        tvForAway.text = item?.get(0).strAwayLineupForward

        tvSubHome.text = item?.get(0).strHomeLineupSubstitutes
        tvSubAway.text = item?.get(0).strAwayLineupSubstitutes

    }
}



