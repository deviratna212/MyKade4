package id.ac.uty.mykade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.uty.mykade.DetailActivity
import id.ac.uty.mykade.adapter.FavoriteAdapter
import id.ac.uty.mykade.database.database
import id.ac.uty.mykade.invisible
import org.jetbrains.anko.db.select
import id.ac.uty.mykade.model.Favorite
import kotlinx.android.synthetic.main.fragment_fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import id.ac.uty.mykade.*


class FragmentFavorite : Fragment() {
    private var favorites : MutableList<Favorite> = mutableListOf()
    private lateinit var adapter : FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getTeamFavorite()
        adapter = FavoriteAdapter(favorites, listener =  {
            ctx.startActivity<DetailActivity>("item" to "${it.idEvent}")
        })
        rvFavFootball.layoutManager = LinearLayoutManager(activity)
        rvFavFootball.adapter = adapter


    }

    private fun getTeamFavorite(){
        progress_bar_fav.invisible()
        favorites.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORIT)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            //adapter.notifyDataSetChanged()

            Log.d("lololo frag fav db", "favorite " + favorite.size + result.tableName)
        }
    }

    override fun onResume() {
        super.onResume()
        getTeamFavorite()
    }
}
