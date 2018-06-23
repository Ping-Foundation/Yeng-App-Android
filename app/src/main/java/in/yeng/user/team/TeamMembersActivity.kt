package `in`.yeng.user.team

import `in`.yeng.user.R
import `in`.yeng.user.helpers.ConnectivityHelper
import `in`.yeng.user.helpers.viewbinders.BinderSection
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.newsupdates.helpers.ProfileAdapter
import `in`.yeng.user.team.dom.GroupMember
import `in`.yeng.user.team.network.TeamMembersListAPI
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import com.wang.avi.AVLoadingIndicatorView
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter
import kotlinx.android.synthetic.main.team_member_activity.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

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
            it.subtitle = "Members"
        }

        loadingIndicator = findViewById(R.id.loading_indicator)

        ConnectivityHelper.ifNotConnected(this) {
            noConnection.visibility = View.VISIBLE
            Handler().postDelayed({ loadingIndicator.smoothToHide() }, 250)
        }

        ConnectivityHelper.ifConnected(this) {
            noConnection.visibility = View.GONE
        }

        id = intent.getStringExtra("id")

        recyclerView = findViewById(R.id.recycler_view)

        val layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        else GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager


        val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()
        recyclerView.adapter = adapter

        loadingIndicator.smoothToShow()

        retryConnect.setOnClickListener {
            TeamMembersListAPI.withListOfTeamMembers(id) { profiles ->
                bindProfiles(profiles, adapter)
            }
        }

        TeamMembersListAPI.withListOfTeamMembers(id) { profiles ->
            bindProfiles(profiles, adapter)
        }


    }

    fun bindProfiles(profiles: List<GroupMember>, adapter: RecyclerBinderAdapter<BinderSection, BinderTypes>) {


        ConnectivityHelper.ifNotConnected(this) {
            noConnection.visibility = View.VISIBLE
            Handler().postDelayed({ loadingIndicator.smoothToHide() }, 250)
        }

        ConnectivityHelper.ifConnected(this) {
            noConnection.visibility = View.GONE
        }

        if (profiles.isEmpty())
            emptyContent.visibility = View.VISIBLE
        else
            for (item in profiles) {
                if (item.status)
                    adapter.add(BinderSection.SECTION_1, ProfileAdapter(this, item))
            }
        loadingIndicator.smoothToHide()
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

    // For custom font
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
