package `in`.yeng.user.team

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.team_member_activity.*
import kotlinx.android.synthetic.main.team_member_profile_view_content.*

class ProfileViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_member_profile_view)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        val name = findViewById<TextView>(R.id.name)
        val profilePic = findViewById<CircleImageView>(R.id.profile_pic)
        val appbar = findViewById<AppBarLayout>(R.id.appbar_layout)

        name.text = intent.getStringExtra("name")
        Glide.with(this).load(intent.getStringExtra("profilePic")).into(profilePic)

        AnimUtil.fadeUp(name, 900)
        AnimUtil.fadeDown(profilePic, 800)
        AnimUtil.fadeDown(appbar, 600, 500f)

        AnimUtil.fadeUp(linearLayout, 700)
        AnimUtil.fadeUp(linearLayout2, 800)
        AnimUtil.fadeUp(linearLayout3, 800)
    }


    /*
    For showing Backbutton over Toolbar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                finish()
        }
        return true
    }
}