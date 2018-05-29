package `in`.yeng.user.Activities

import `in`.yeng.user.R
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.builders.footer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.expandable.expandableItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        setupDrawer()
    }


    fun setupDrawer() {


        var drawerLayout = drawer {
            this.toolbar = this@MainActivity.toolbar
            accountHeader {
                translucentStatusBar = true
                backgroundScaleType = ImageView.ScaleType.FIT_START
                background = R.drawable.navigation_header
            }

            primaryItem("News & Updates")
            expandableItem("Syllabus") {
                primaryItem("Btech")
                primaryItem ("MTech")
        }
            primaryItem("Join Us")

            footer {
                primaryItem("Team")
            }
        }

    }
}
