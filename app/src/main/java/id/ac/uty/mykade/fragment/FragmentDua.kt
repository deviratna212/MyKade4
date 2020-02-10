package id.ac.uty.mykade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import id.ac.uty.mykade.DetailActivity
import id.ac.uty.mykade.adapter.MainAdapter
import id.ac.uty.mykade.interfaces.MainView
import id.ac.uty.mykade.invisible
import id.ac.uty.mykade.model.Event
import id.ac.uty.mykade.presenters.MainPresenter
import kotlinx.android.synthetic.main.fragment_fragment_satu.*
import org.jetbrains.anko.support.v4.intentFor
import id.ac.uty.mykade.network.*
import id.ac.uty.mykade.visible
import id.ac.uty.mykade.*
import kotlinx.android.synthetic.main.fragment_fragment_dua.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentDua : Fragment(), MainView {

    private var items : MutableList<Event> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter : MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_dua, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
        adapter = MainAdapter(activity!!, items, listener =  {
            //toast("CLub : ${it.teamName}")
            startActivity(intentFor<DetailActivity>("item" to it.idEvent))
        })
        rvFootball.layoutManager = LinearLayoutManager(activity)
        rvFootball.adapter = adapter
    }

    fun initData(){
        val request = ApiRepository()
        val gson = Gson()

        presenter = MainPresenter(this, request, gson)
        presenter.getItemNext("4328")
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.visible()
    }

    override fun getTeamList(data: List<Event>) {
        progress_bar.invisible()
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
