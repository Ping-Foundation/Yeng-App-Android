package `in`.yeng.user.team

import `in`.yeng.user.R
import `in`.yeng.user.helpers.viewbinders.BinderSection
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.newsupdates.helpers.ProfileAdaptor
import `in`.yeng.user.newsupdates.helpers.TeamAdaptor
import `in`.yeng.user.team.dom.Profile
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter

class TeamFragment : Fragment() {

    companion object {
        val TAG = "TeamFragment"
    }

    private var _context: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        _context = context
    }

    override fun onDetach() {
        super.onDetach()
        _context = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.team_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeYengTeam(view)

        initializeTeam(view)


    }

    fun initializeYengTeam(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.yeng_team_recyclerveiw)
        recyclerView.isNestedScrollingEnabled = true

        val layoutManager = LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()
        recyclerView.adapter = adapter


        val profiles = listOf<Profile>(
                Profile("Android Team", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/APK_format_icon.png/240px-APK_format_icon.png"),
                Profile("IOS Team", "https://www.freeiconspng.com/uploads/apple-ios-png-12.png"),
                Profile("Server Team", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/AWS_Simple_Icons_Non-Service_Specific_Traditional_Server.svg/200px-AWS_Simple_Icons_Non-Service_Specific_Traditional_Server.svg.png"),
                Profile("Designing Team", "https://cms-assets.tutsplus.com/uploads/users/769/posts/29018/preview_image/realm%20database.png")
        )

        for (item in profiles)
            adapter.add(BinderSection.SECTION_1, TeamAdaptor(_context as AppCompatActivity, item))
    }

    fun initializeTeam(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.yeng_learning_team_recyclerveiw)
        recyclerView.isNestedScrollingEnabled = true

        val layoutManager = LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()
        recyclerView.adapter = adapter


        val profiles = listOf<Profile>(
                Profile("Android Team", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/APK_format_icon.png/240px-APK_format_icon.png"),
                Profile("IOS Team", "https://www.freeiconspng.com/uploads/apple-ios-png-12.png"),
                Profile("Server Team", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/AWS_Simple_Icons_Non-Service_Specific_Traditional_Server.svg/200px-AWS_Simple_Icons_Non-Service_Specific_Traditional_Server.svg.png"),
                Profile("Designing Team", "https://cms-assets.tutsplus.com/uploads/users/769/posts/29018/preview_image/realm%20database.png")
        )

        for (item in profiles)
            adapter.add(BinderSection.SECTION_1, TeamAdaptor(_context as AppCompatActivity, item))
    }
}
