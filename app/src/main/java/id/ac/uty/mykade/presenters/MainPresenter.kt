package id.ac.uty.mykade.presenters

import android.util.Log
import com.google.gson.Gson
import id.ac.uty.mykade.interfaces.MainView
import id.ac.uty.mykade.response.ItemResponse
import id.ac.uty.mykade.network.ApiRepository
import id.ac.uty.mykade.network.TheSportDBApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter (private val view : MainView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getItemPrevious(id: String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSchedulePrev(id)),
                ItemResponse::class.java)

            uiThread {
                view.hideLoading()
                view.getTeamList(data.events)
            }
            Log.d("lololo prev data", "$data")

        }
    }

    fun getItemNext(id: String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getScheduleNext(id)),
                ItemResponse::class.java)

            uiThread {
                view.hideLoading()
                view.getTeamList(data.events)
            }
            Log.d("lololo next data", "$data")

        }
    }
}