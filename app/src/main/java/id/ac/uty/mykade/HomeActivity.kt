package id.ac.uty.mykade

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.ac.uty.mykade.fragment.BlankFragment
import id.ac.uty.mykade.fragment.FragmentFavorite
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener{
            item -> when (item.itemId){
                R.id.navigation_home -> {
                    loadFragmentSatu(savedInstanceState)
                }
                R.id.navigation_dashboard -> {
                    loadFragmentFavorit(savedInstanceState)
                }
            }
            true
        }
        navigation.selectedItemId = R.id.navigation_home
    }

    private fun loadFragmentSatu(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameHome,
                    BlankFragment(), BlankFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFragmentFavorit(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameHome,
                    FragmentFavorite(), FragmentFavorite::class.java.simpleName)
                .commit()
        }
    }
}
