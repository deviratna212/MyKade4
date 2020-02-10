package id.ac.uty.mykade


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.ac.uty.mykade.adapter.DetailContentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_team_details.*

class DetailTeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        setView()
    }

    private fun setView(){

        val adapter = DetailContentAdapter(this.supportFragmentManager)
        viewpager_contentTeamDeatail.adapter = adapter

        tabs_contentTeamDetail.setupWithViewPager(viewpager_main)
    }
}
