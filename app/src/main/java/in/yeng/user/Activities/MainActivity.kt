package `in`.yeng.user.Activities

import `in`.yeng.user.API.APIClient
import `in`.yeng.user.Fragments.FragNews
import `in`.yeng.user.Fragments.FragSyllabus
import `in`.yeng.user.R
import `in`.yeng.user.Utilities.FragmentHelper
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.builders.footer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import co.zsmb.materialdrawerkt.draweritems.expandable.expandableItem
import com.wang.avi.AVLoadingIndicatorView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var loadingIndicator: AVLoadingIndicatorView
        val CONTAINER_LAYOUT = R.id.fragment_container
    }


    var doubleBackToExitPressedOnce = false

    var fragNews: FragNews? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        setupDrawer()

        loadingIndicator = loading_indicator


    }


    fun setupDrawer() {
        drawer {
            this.toolbar = this@MainActivity.toolbar
            accountHeader {

                translucentStatusBar = true
                backgroundScaleType = ImageView.ScaleType.FIT_START
                background = R.drawable.navigation_header

            }
            primaryItem("News & Updates") {

                icon = R.drawable.ic_update

                // Load this by default ..
                FragmentHelper.ReplaceFragment(FragNews(), this@MainActivity, CONTAINER_LAYOUT, FragNews.TAG, 250)

                // Load if clicked..
                onClick { _ ->
                    if (fragNews == null)
                        fragNews = FragNews()
                    fragNews?.let { FragmentHelper.ReplaceFragment(it, this@MainActivity, CONTAINER_LAYOUT, FragNews.TAG, 250) }
                    false
                }

            }
            expandableItem("Syllabus") {

                icon = R.drawable.ic_syllabus

                // Asynchronously crete sub list of syllabus.
                APIClient.getSyllabusList("Syllabus") { syllabuses, _ ->
                    for (item in syllabuses)
                        primaryItem(item) {
                            icon = R.drawable.ic_syllabuses

                            onClick { _ ->
                                val fragSyllabus = FragSyllabus()
                                fragSyllabus.arguments = Bundle().apply {
                                    putString("id", item)
                                    FragmentHelper.ReplaceFragment(fragSyllabus, this@MainActivity, CONTAINER_LAYOUT, FragSyllabus.TAG.plus("1"), 250)
                                    supportActionBar?.let { it.title = item.toUpperCase() }
                                }
                                false
                            }
                        }

                    if (loadingIndicator.isShown)
                        loadingIndicator.smoothToHide()
                }

            }
            divider { }
            primaryItem("Join Us") {
                icon = R.drawable.ic_join_us
            }
            footer {
                primaryItem("Team") {
                    icon = R.drawable.ic_group
                }
            }
        }
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
            toast("Please click BACK again to exit")
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)

        }
    }
}
