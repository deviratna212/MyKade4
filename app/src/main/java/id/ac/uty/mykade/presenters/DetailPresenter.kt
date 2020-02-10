package id.ac.uty.mykade.presenters

import android.util.Log
import com.google.gson.Gson
import id.ac.uty.mykade.interfaces.DetailView
import id.ac.uty.mykade.network.ApiRepository
import org.jetbrains.anko.doAsync
import id.ac.uty.mykade.response.*
import id.ac.uty.mykade.network.*
import org.jetbrains.anko.uiThread

class DetailPresenter
    (private val view : DetailView,
     private val apiRepository: ApiRepository,
     private val gson : Gson){

    fun getLogoHome(id: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamLogo(id)),
                ItemResponse::class.java)

            uiThread {
                view.hideLoading()
                view.getHomeLogo(data.teams)
            }
        }
    }

    fun getLogoAway(id: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamLogo(id)),
                ItemResponse::class.java)

            uiThread {
                view.hideLoading()
                view.getAwayLogo(data.teams)
            }
        }
    }

    fun getDetailEvent(id: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetail(id)),
                ItemResponse::class.java)

            uiThread {
                view.hideLoading()
                view.getEventDetail(data.events)
                Log.d("lololo detail Event", "$data")
            }
        }
    }
}