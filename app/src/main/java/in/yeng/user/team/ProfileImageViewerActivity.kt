package `in`.yeng.user.team

import `in`.yeng.user.R
import android.app.ActionBar
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.WindowManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.profile_image_viewer_content.*
import kotlinx.android.synthetic.main.profile_image_viewer_activity.*

class ProfileImageViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_image_viewer_activity)
        setSupportActionBar(toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = intent.getStringExtra("name")
            it.setDisplayHomeAsUpEnabled(true)
        }

        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

        Glide.with(this).load(intent.getStringExtra("profile_pic")).into(profile_image)
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