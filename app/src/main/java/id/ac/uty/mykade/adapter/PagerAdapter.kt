package id.ac.uty.mykade.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import id.ac.uty.mykade.fragment.FragmentDua
import id.ac.uty.mykade.fragment.FragmentSatu

class PagerAdapter(fm: FragmentManager) :
FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                FragmentSatu()
            }
            else -> {
                return FragmentDua()
            }
        }

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Prev Event"
            else -> {
                return "Next Event"
            }
        }
    }
}