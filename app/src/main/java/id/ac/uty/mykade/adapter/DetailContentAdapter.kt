package id.ac.uty.mykade.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import id.ac.uty.mykade.DescTeamFragment
import id.ac.uty.mykade.TeamPlayersFragment

class DetailContentAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                DescTeamFragment()
            }
            else -> {
                return TeamPlayersFragment()
            }
        }

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Deskription"
            else -> {
                return "Players"
            }
        }
    }
}