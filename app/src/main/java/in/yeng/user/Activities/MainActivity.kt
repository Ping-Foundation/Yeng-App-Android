package `in`.yeng.user.Activities

import `in`.yeng.user.API.APIClient
import `in`.yeng.user.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.builders.footer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import co.zsmb.materialdrawerkt.draweritems.expandable.expandableItem
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        drawerLayout = setupDrawer()

        loading_indicator.smoothToShow()


    }



    fun setupDrawer() = drawer {
        this.toolbar = this@MainActivity.toolbar
        accountHeader {
            translucentStatusBar = true
            backgroundScaleType = ImageView.ScaleType.FIT_START
            background = R.drawable.navigation_header
        }
        primaryItem("News & Updates") {
            identifier = 0
        }
        expandableItem("Syllabus") {
            APIClient.getSyllabusArray {
                for (item in it)
                    primaryItem(item)
                loading_indicator.smoothToHide()
            }
        }
        divider { }
        primaryItem("Join Us") {
            identifier = 2

        }

        footer {
            primaryItem("Team") {
                identifier = 3
            }
        }
    }
}
