package `in`.yeng.user.Activities

import `in`.yeng.user.API.APIClient
import `in`.yeng.user.Fragments.FragNewsAndUpdates
import `in`.yeng.user.Fragments.FragSyllabus
import `in`.yeng.user.R
import `in`.yeng.user.Utilities.FragmentHelper
import android.os.Bundle
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


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var loadingIndicator: AVLoadingIndicatorView
        val CONTAINER_LAYOUT = R.id.fragment_container
    }




    var fragNewsAndUpdates: FragNewsAndUpdates? = null

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
                FragmentHelper.ReplaceFragment(FragNewsAndUpdates(), this@MainActivity, CONTAINER_LAYOUT, 250)

                // Load if clicked..
                onClick { _ ->
                    if (fragNewsAndUpdates == null)
                        fragNewsAndUpdates = FragNewsAndUpdates()
                    fragNewsAndUpdates?.let { FragmentHelper.ReplaceFragment(it, this@MainActivity, CONTAINER_LAYOUT, 250) }
                    false
                }

            }
            expandableItem("Syllabus") {

                icon = R.drawable.ic_syllabus

                // Asynchronously crete sub list of syllabus.
                APIClient.getSyllabusList("Syllabus") {
                    for (item in it)
                        primaryItem(item) {
                            icon = R.drawable.ic_syllabuses

                            onClick { _ ->
                                val fragSyllabus = FragSyllabus()
                                fragSyllabus.arguments = Bundle().apply {
                                    putString("id", item)
                                    FragmentHelper.ReplaceFragment(fragSyllabus, this@MainActivity, CONTAINER_LAYOUT, 250)
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
}
