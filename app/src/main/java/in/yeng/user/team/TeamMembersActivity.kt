package `in`.yeng.user.team

import `in`.yeng.user.R
import `in`.yeng.user.helpers.viewbinders.BinderSection
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.newsupdates.helpers.ProfileAdaptor
import `in`.yeng.user.team.dom.Profile
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter
import kotlinx.android.synthetic.main.team_member_activity.*

class TeamMembersActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_member_activity)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = intent.getStringExtra("name")
        }

        recyclerView = findViewById(R.id.recycler_view)

        val layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val profiles = listOf(
                Profile("Alexander", "https://randomuser.me/api/portraits/men/81.jpg"),
                Profile("Untitled", "https://randomuser.me/api/portraits/women/84.jpg"),
                Profile("Backspace", "https://randomuser.me/api/portraits/men/81.jpg"),
                Profile("Random Guy", "https://randomuser.me/api/portraits/men/33.jpg"),
                Profile("No Idea", "https://randomuser.me/api/portraits/women/63.jpg"),
                Profile("Whose this", "https://randomuser.me/api/portraits/women/13.jpg"),
                Profile("disconnect failed", "https://randomuser.me/api/portraits/men/87.jpg"),
                Profile("EGLNativeWindowType", "https://randomuser.me/api/portraits/men/64.jpg")
        )

        val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()
        recyclerView.adapter = adapter

        for (item in profiles)
            adapter.add(BinderSection.SECTION_1, ProfileAdaptor(this, item))

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
