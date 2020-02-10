package id.ac.uty.mykade.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import id.ac.uty.mykade.R
import id.ac.uty.mykade.model.Favorite
import org.jetbrains.anko.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class FavoriteAdapter (private val favorite:List<Favorite>,
                       private val listener: (Favorite)-> Unit)
    :RecyclerView.Adapter<FavViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): FavViewHolder {
        return FavViewHolder(FavoriteUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount():  Int { return favorite.size }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bindItem(favorite[position],listener)
    }
}

class FavViewHolder(val view: View)
    : RecyclerView.ViewHolder(view) {
    private val tvFavDate: TextView = view.find(R.id.tvFavDate)
    private val tvFavTeam1: TextView = view.find(R.id.tvFavTeam1)
    private val tvFavScore1: TextView = view.find(R.id.tvFavScore1)
    private val tvFavScore2: TextView = view.find(R.id.tvFavScore2)
    private val tvFavTeam2: TextView = view.find(R.id.tvFavTeam2)

    fun bindItem(itemnya: Favorite, listener: (Favorite) -> Unit) {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val newDateFormat: DateFormat = SimpleDateFormat("E, dd MMM yyyy")

        val date = dateFormat.parse(itemnya.dateEvent)

        tvFavDate.text = newDateFormat.format(date)
        tvFavTeam1.text = itemnya.strHomeTeam
        tvFavTeam2.text = itemnya.strAwayTeam
        tvFavScore1.text = itemnya.intHomeScore
        tvFavScore2.text = itemnya.intAwayScore

        Log.d("lololo adapter fav db", "favorite " + itemnya.intAwayScore)

        itemView.setOnClickListener {
            listener(itemnya)
        }
    }
}

class FavoriteUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(10)

                    textView("Date of match") {
                        id = R.id.tvFavDate
                        textColor = R.color.colorAccent
                    }.lparams(width = wrapContent, height = wrapContent) {
                        gravity = Gravity.CENTER
                        margin = dip(8)
                    }
                    linearLayout {
                        gravity = Gravity.CENTER
                        orientation = LinearLayout.HORIZONTAL

                        textView("TeamHome") {
                            gravity = Gravity.CENTER
                            id = R.id.tvFavTeam1
                            rightPadding = dip(5)
                        }.lparams(width = dip(0), height = wrapContent) {
                            gravity = Gravity.CENTER
                            weight = 1F
                            margin = dip(3)
                        }
                        linearLayout {
                            gravity = Gravity.CENTER
                            orientation = LinearLayout.HORIZONTAL

                            textView("0") {
                                id = R.id.tvFavScore1
                                leftPadding = dip(5)
                                rightPadding = dip(5)

                            }.lparams(width = wrapContent, height = wrapContent)
                            textView("vs") {
                                leftPadding = dip(5)
                                rightPadding = dip(5)
                            }.lparams(width = wrapContent, height = wrapContent)
                            textView("0") {
                                id = R.id.tvFavScore2
                                leftPadding = dip(5)
                                rightPadding = dip(5)
                            }.lparams(width = wrapContent, height = wrapContent)
                        }.lparams(width = dip(0), height = wrapContent) {
                            gravity = Gravity.CENTER
                            weight = 1F
                        }
                        textView("TeamAway") {
                            gravity = Gravity.CENTER
                            id = R.id.tvFavTeam2
                            rightPadding = dip(5)
                        }.lparams(width = dip(0), height = wrapContent) {
                            gravity = Gravity.CENTER
                            weight = 1F
                            margin = dip(3)
                        }
                    }.lparams(width = matchParent, height = wrapContent) {
                        gravity = Gravity.CENTER
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    centerInParent()
                }
            }
        }
    }
}