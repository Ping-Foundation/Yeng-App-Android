package `in`.yeng.user.team

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.team.dom.Profile
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.WindowManager
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.siyamed.shapeimageview.CircularImageView
import kotlinx.android.synthetic.main.team_member_activity.*
import kotlinx.android.synthetic.main.team_member_profile_view_content.*
import org.jetbrains.anko.intentFor

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_member_profile_view)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = ""
            it.setDisplayHomeAsUpEnabled(true)
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

        val name = findViewById<TextView>(R.id.name)
        val profilePic = findViewById<CircularImageView>(R.id.profile_pic)
        val appbar = findViewById<AppBarLayout>(R.id.appbar_layout)
        val email = findViewById<TextView>(R.id.email)
        val phone = findViewById<TextView>(R.id.phone_number)
        val location = findViewById<TextView>(R.id.location)
        val joinTelegram = findViewById<TextView>(R.id.join_telegram)

        val profile: Profile = intent.getSerializableExtra("data") as Profile

        name.text = profile.name
        location.text = profile.address
        email.text = profile.email
        phone.text = profile.mob
        Glide.with(this).load(profile.profilePic).into(profilePic)

        email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", profile.email,""))
            startActivity(Intent.createChooser(intent, "Send email..."))
        }
        phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", profile.mob,""))
            startActivity(intent)
        }
        profilePic.setOnClickListener {
            startActivity(intentFor<ProfileImageViewerActivity>("profile_pic" to profile.profilePic, "name" to profile.name))
        }
        joinTelegram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/pzy64"))
            startActivity(intent)
        }

        AnimUtil.fadeUp(name, 900)
        AnimUtil.fadeDown(profilePic, 800)
        AnimUtil.fadeDown(appbar, 600, 500f)

        AnimUtil.fadeUp(linearLayout, 700)
        AnimUtil.fadeUp(linearLayout2, 800)
        AnimUtil.fadeUp(linearLayout3, 800)
        AnimUtil.fadeUp(linearLayout4, 1000)
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