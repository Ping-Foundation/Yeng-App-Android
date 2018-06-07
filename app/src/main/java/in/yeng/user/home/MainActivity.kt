package `in`.yeng.user.home

import `in`.yeng.user.R
import `in`.yeng.user.helpers.FragmentHelper
import `in`.yeng.user.newsupdates.JoinWithUsFragment
import `in`.yeng.user.newsupdates.NewsUpdateFragment
import `in`.yeng.user.syllabus.SyllabusFragment
import `in`.yeng.user.syllabus.network.SyllabusAPI
import `in`.yeng.user.team.TeamFragment
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.builders.footer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import co.zsmb.materialdrawerkt.draweritems.expandable.expandableItem
import com.wang.avi.AVLoadingIndicatorView
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import android.R.string.ok
import android.widget.Button
import com.kennyc.bottomsheet.BottomSheet




class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var loadingIndicator: AVLoadingIndicatorView


        val CONTAINER_LAYOUT = R.id.fragment_container
    }

    lateinit var appbarLayout: AppBarLayout
    var height: Int = 120
    lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    lateinit var toolbar: Toolbar
    lateinit var coverImage: ImageView

    var doubleBackToExitPressedOnce = false

    var newsUpdateFragment: NewsUpdateFragment? = null
    var teamFragment: TeamFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        toolbar = findViewById(R.id.toolbar)
        appbarLayout = findViewById(R.id.appbar_layout)
        height = appbarLayout.layoutParams.height
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        loadingIndicator = findViewById(R.id.loading_indicator)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        setupDrawer()

    }


    fun setupDrawer() {
        val drawer = drawer {

            actionBarDrawerToggleEnabled = true

            accountHeader {

                translucentStatusBar = true
                backgroundScaleType = ImageView.ScaleType.FIT_START
                background = R.drawable.nav_header

            }
            primaryItem("News & Updates") {

                icon = R.drawable.ic_update

                // Load this by default ..
                FragmentHelper.ReplaceFragment(NewsUpdateFragment(), this@MainActivity, CONTAINER_LAYOUT, NewsUpdateFragment.TAG, 250)
                supportActionBar?.let { it.title = "News & Updates" }

                // Load if clicked..
                onClick { _ ->
                    if (newsUpdateFragment == null)
                        newsUpdateFragment = NewsUpdateFragment()
                    newsUpdateFragment?.let { FragmentHelper.ReplaceFragment(it, this@MainActivity, CONTAINER_LAYOUT, NewsUpdateFragment.TAG, 250) }
                    supportActionBar?.let { it.title = "News & Updates" }
                    false
                }

            }
            expandableItem("Syllabus") {

                icon = R.drawable.ic_syllabus

                // Asynchronously crete sub list of syllabus.
                SyllabusAPI.getSyllabusList("Syllabus") { syllabuses, _ ->
                    for (item in syllabuses)
                        primaryItem(item) {
                            icon = R.drawable.ic_syllabuses

                            onClick { _ ->
                                val fragSyllabus = SyllabusFragment()
                                fragSyllabus.arguments = Bundle().apply { putString("id", item) }
                                FragmentHelper.ReplaceFragment(fragSyllabus, this@MainActivity, CONTAINER_LAYOUT, SyllabusFragment.TAG.plus("1"), 250)
                                supportActionBar?.let { it.title = item.toUpperCase() }
                                false
                            }
                        }

                    if (loadingIndicator.isShown)
                        loadingIndicator.smoothToHide()
                }

            }
            divider { }
            primaryItem("Join With Us") {
                icon = R.drawable.ic_join_us

                onClick { _ ->
                    FragmentHelper.ReplaceFragment(JoinWithUsFragment(), this@MainActivity, CONTAINER_LAYOUT, JoinWithUsFragment.TAG, 250)
                    supportActionBar?.let { it.title = " " }
                    false
                }

            }
            footer {
                primaryItem("Team") {
                    icon = R.drawable.ic_group

                    onClick { _ ->
                        if (teamFragment == null)
                            teamFragment = TeamFragment()
                        teamFragment?.let { FragmentHelper.ReplaceFragment(it, this@MainActivity, CONTAINER_LAYOUT, TeamFragment.TAG, 250) }
                        supportActionBar?.let { it.title = "Team" }
                        false
                    }
                }
            }
        }
        drawer.actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer.drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed)
    }

    override fun onBackPressed() {
        /*
        Remove all fragments over base fragment before app exit (specifically made for syllabus listing)
         */
        if (FragmentHelper.fragCount > 1) {
            val frags = supportFragmentManager.fragments
            FragmentHelper.RemoveFragment(frags[frags.lastIndex], this, 250)
        } else {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            doubleBackToExitPressedOnce = true
            Snackbar.make(appbarLayout, "Please click BACK again to exit", Snackbar.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)

        }
    }


    // For custom font
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
