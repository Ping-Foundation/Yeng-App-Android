package `in`.yeng.user.team

import `in`.yeng.user.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.team_member_activity.*
import org.jetbrains.anko.find

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

        name.text = intent.getStringExtra("name")
        Glide.with(this).load(intent.getStringExtra("profilePic")).into(profilePic)

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