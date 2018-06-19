package `in`.yeng.user.team

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.network.APIClient
import `in`.yeng.user.team.network.ProfileAPI
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
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
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val id: String = intent.getStringExtra("id")


        ProfileAPI.withProfile(id) {
            name.text = it.name
            phone.text = it.mob
            joinTelegram.text = "https://t.me/".plus(it.telegram)
            location.text = it.place
            email.text = it.email


            if (it.email.isNotEmpty()) {
                emailLayout.visibility = View.VISIBLE
                emailLayout.setOnClickListener { _ ->
                    val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", it.email, ""))
                    startActivity(Intent.createChooser(intent, "Send email..."))
                }
            }

            if (it.mob.isNotEmpty()) {
                callLayout.visibility = View.VISIBLE
                callLayout.setOnClickListener { _ ->
                    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", it.mob, ""))
                    startActivity(intent)
                }
            }

            if (it.telegram.isNotEmpty()) {
                telegramLayout.visibility = View.VISIBLE

                joinTelegram.setOnClickListener { _ ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/".plus(it.telegram)))
                    startActivity(intent)
                }
            }

            if (it.place.isNotEmpty()) {
                locationLayout.visibility = View.VISIBLE
            }

            if (it.image.isNotEmpty()) {
                Glide.with(this).load(APIClient.CRAZY_AMIGOS_BASEURL.plus(it.image)).into(profilePic)
                profilePic.setOnClickListener { _ ->
                    startActivity(intentFor<ProfileImageViewerActivity>("profile_pic" to APIClient.CRAZY_AMIGOS_BASEURL.plus(it.image), "name" to it.name))
                }

            }


        }

        AnimUtil.fadeUp(name, 900)
        AnimUtil.fadeDown(profilePic, 800)
        AnimUtil.fadeDown(appbar, 600, 500f)

        AnimUtil.fadeUp(emailLayout, 700)
        AnimUtil.fadeUp(callLayout, 800)
        AnimUtil.fadeUp(locationLayout, 800)
        AnimUtil.fadeUp(telegramLayout, 1000)
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