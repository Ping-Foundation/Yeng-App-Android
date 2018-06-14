package `in`.yeng.user.team

import `in`.yeng.user.R
import `in`.yeng.user.helpers.viewbinders.BinderSection
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.newsupdates.helpers.ProfileAdaptor
import `in`.yeng.user.team.network.TeamMembersListAPI
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.wang.avi.AVLoadingIndicatorView
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter
import kotlinx.android.synthetic.main.team_member_activity.*

class TeamMembersActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var id: String

    companion object {
        lateinit var loadingIndicator: AVLoadingIndicatorView

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_member_activity)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = intent.getStringExtra("name")
        }

        loadingIndicator = findViewById(R.id.loading_indicator)

        id = intent.getStringExtra("id")

        recyclerView = findViewById(R.id.recycler_view)

        val layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        else GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager


        val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()
        recyclerView.adapter = adapter

        loadingIndicator.smoothToShow()
        TeamMembersListAPI.getTeamList(id) { profiles ->
            for (item in profiles)
                adapter.add(BinderSection.SECTION_1, ProfileAdaptor(this, item))
            loadingIndicator.smoothToHide()
        }


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
