package id.ac.uty.mykade.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import id.ac.uty.mykade.R
import id.ac.uty.mykade.model.Event
import org.jetbrains.anko.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class MainAdapter (private val context: Context,
                   private val events:List<Event>,
                   private val listener: (Event)-> Unit)
                   :RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(ItemsUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount():  Int { return events.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(events[position],listener)
    }
}

class ViewHolder(val view: View)
    : RecyclerView.ViewHolder(view) {
    private val tvDate: TextView = view.find(R.id.tvDate)
    private val tvTeam1: TextView = view.find(R.id.tvTeam1)
    private val tvScore1: TextView = view.find(R.id.tvScore1)
    private val tvScore2: TextView = view.find(R.id.tvScore2)
    private val tvTeam2: TextView = view.find(R.id.tvTeam2)

    fun bindItem(itemnya: Event, listener: (Event) -> Unit) {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val newDateFormat: DateFormat = SimpleDateFormat("E, dd MMM yyyy")

        val date = dateFormat.parse(itemnya.dateEvent)

        tvDate.text = newDateFormat.format(date)
        tvTeam1.text = itemnya.strHomeTeam
        tvTeam2.text = itemnya.strAwayTeam
        tvScore1.text = itemnya.intHomeScore
        tvScore2.text = itemnya.intAwayScore

        itemView.setOnClickListener {
            listener(itemnya)
        }
    }
}

class ItemsUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(10)

                    textView("Date of match") {
                        id = R.id.tvDate
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
                            id = R.id.tvTeam1
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
                                id = R.id.tvScore1
                                leftPadding = dip(5)
                                rightPadding = dip(5)

                            }.lparams(width = wrapContent, height = wrapContent)
                            textView("vs") {
                                leftPadding = dip(5)
                                rightPadding = dip(5)
                            }.lparams(width = wrapContent, height = wrapContent)
                            textView("0") {
                                id = R.id.tvScore2
                                leftPadding = dip(5)
                                rightPadding = dip(5)
                            }.lparams(width = wrapContent, height = wrapContent)
                        }.lparams(width = dip(0), height = wrapContent) {
                            gravity = Gravity.CENTER
                            weight = 1F
                        }
                        textView("TeamAway") {
                            gravity = Gravity.CENTER
                            id = R.id.tvTeam2
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