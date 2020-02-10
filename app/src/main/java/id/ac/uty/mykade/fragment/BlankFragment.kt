package id.ac.uty.mykade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import id.ac.uty.mykade.adapter.*
import id.ac.uty.mykade.*


/**
 * A simple [Fragment] subclass.
 *
 */
class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentInFragmentAdapter = PagerAdapter(childFragmentManager)
        viewpager_main.adapter = fragmentInFragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }


}
