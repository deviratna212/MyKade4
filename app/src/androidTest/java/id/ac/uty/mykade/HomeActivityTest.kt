package id.ac.uty.mykade

import android.os.SystemClock
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.support.test.espresso.Espresso.pressBack

import org.junit.Rule
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import id.ac.uty.mykade.R.id.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)


    @Test
    fun testAppBehaviour(){

        //aplikasi terbuka dan menampilkan view dari fragment prev event
        //memastikan bahwa buttom navigation dapat ditampilkan
        onView(withId(navigation))
            .check(matches(isDisplayed()))
        onView(withId(navigation_home))
            .check(matches(isDisplayed()))

        //memberi jeda waktu selama 5 second agar data dapat diload pada view
        SystemClock.sleep(5000)

        //memastikan bahwa recyclerview dapat ditampilkan
        //memastilan bahwa recyclerview dapat discroll pada posisi item ke 3
        //memastikan bahwa recyclerview dapat di lakukan aksi klik pada posisi item ke 3
        onView(withId(rvFootballPrev))
            .check(matches(isDisplayed()))
        onView(withId(rvFootballPrev))
            .check(matches(isDisplayed()))
        onView(withId(rvFootballPrev))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(rvFootballPrev))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        //memberi jeda waktu selama 5 second agar data dapat diload pada view
        SystemClock.sleep(5000)

        //memastikan bahwa semua komponen detail seperti textview dan image dapat ditampilkan
        onView(withId(tvDate))
            .check(matches(isDisplayed()))
        onView(withId(imageHome))
            .check(matches(isDisplayed()))
        onView(withId(tvTeamHome))
            .check(matches(isDisplayed()))
        onView(withId(tvStrAway))
            .check(matches(isDisplayed()))
        onView(withId(tvScoreHome))
            .check(matches(isDisplayed()))
        onView(withId(tvScoreAway))
            .check(matches(isDisplayed()))
        onView(withId(image_away))
            .check(matches(isDisplayed()))
        onView(withId(tvStrAwayRight))
            .check(matches(isDisplayed()))
        onView(withId(tvGolHome))
            .check(matches(isDisplayed()))
        onView(withId(tvGolAway))
            .check(matches(isDisplayed()))
        onView(withId(tvShotHome))
            .check(matches(isDisplayed()))
        onView(withId(tvshotsaway))
            .check(matches(isDisplayed()))
        onView(withId(tvKeeperHome))
            .check(matches(isDisplayed()))
        onView(withId(tvKeeperAway))
            .check(matches(isDisplayed()))
        onView(withId(tvDefHome))
            .check(matches(isDisplayed()))
        onView(withId(tvDefAway))
            .check(matches(isDisplayed()))
        onView(withId(tvMidHome))
            .check(matches(isDisplayed()))
        onView(withId(tvMidAway))
            .check(matches(isDisplayed()))
        onView(withId(tvForHome))
            .check(matches(isDisplayed()))
        onView(withId(tvForAway))
            .check(matches(isDisplayed()))
        onView(withId(tvSubHome))
            .check(matches(isDisplayed()))
        onView(withId(tvSubAway))
            .check(matches(isDisplayed()))

        //memberi jeda waktu selama 3 second
        SystemClock.sleep(3000)


        //memastikan bahwa icon favorite dapat ditampilkan dan dapat dilakukan aksi klik
        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
            .perform(click())

        //memberi jeda waktu selama 3 second
        SystemClock.sleep(3000)

        //memastikan bahwa dapat dilakukan aksi back
        pressBack()
    }
}