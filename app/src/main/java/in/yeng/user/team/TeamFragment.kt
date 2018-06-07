package `in`.yeng.user.team

import `in`.yeng.user.R
import `in`.yeng.user.helpers.viewbinders.BinderSection
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.newsupdates.helpers.ProfileAdaptor
import `in`.yeng.user.team.dom.Profile
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
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
        val recyclerView = view.findViewById<RecyclerView>(R.id.yeng_team_recyclerview_initial_list)
        recyclerView.isNestedScrollingEnabled = true

        val layoutManager = GridLayoutManager(_context, 4, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()
        recyclerView.adapter = adapter


        val profiles = listOf<Profile>(
                Profile("name1", ""),
                Profile("name2", ""),
                Profile("name3", ""),
                Profile("name4", ""),
                Profile("name5", ""),
                Profile("name6", ""),
                Profile("name7", ""),
                Profile("name8", ""),
                Profile("name9", ""),
                Profile("name10", ""),
                Profile("name11", ""),
                Profile("name12", ""),
                Profile("name13", ""),
                Profile("name14", "")
        )

        for (item in if (profiles.size >= 4) profiles.subList(0, 4) else profiles)
            adapter.add(BinderSection.SECTION_1, ProfileAdaptor(_context as AppCompatActivity, item))


        val adapterExpanded = RecyclerBinderAdapter<BinderSection, BinderTypes>()

        val recyclerViewExpanded = view.findViewById<RecyclerView>(R.id.yeng_team_recyclerview_later_list)
        recyclerViewExpanded.isNestedScrollingEnabled = true

        val layoutManagerExpanded = GridLayoutManager(_context, 4, GridLayoutManager.VERTICAL, false)
        recyclerViewExpanded.layoutManager = layoutManagerExpanded
        recyclerViewExpanded.adapter = adapterExpanded

        if (profiles.size >= 4)
            for (item in profiles.subList(4, profiles.size))
                adapterExpanded.add(BinderSection.SECTION_1, ProfileAdaptor(_context as AppCompatActivity, item))
    }

    fun initializeTeam(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.team_recyclerview_initial_list)
        recyclerView.isNestedScrollingEnabled = true

        val layoutManager = GridLayoutManager(_context, 4, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()
        recyclerView.adapter = adapter


        val profiles = listOf<Profile>(
                Profile("name1", ""),
                Profile("name2", ""),
                Profile("name3", ""),
                Profile("name4", ""),
                Profile("name5", ""),
                Profile("name6", ""),
                Profile("name7", ""),
                Profile("name8", ""),
                Profile("name9", ""),
                Profile("name10", ""),
                Profile("name11", ""),
                Profile("name12", ""),
                Profile("name13", ""),
                Profile("name14", "")
        )

        for (item in if (profiles.size >= 4) profiles.subList(0, 4) else profiles)
            adapter.add(BinderSection.SECTION_1, ProfileAdaptor(_context as AppCompatActivity, item))


        val adapterExpanded = RecyclerBinderAdapter<BinderSection, BinderTypes>()

        val recyclerViewExpanded = view.findViewById<RecyclerView>(R.id.team_recyclerview_later_list)
        recyclerViewExpanded.isNestedScrollingEnabled = true

        val layoutManagerExpanded = GridLayoutManager(_context, 4, GridLayoutManager.VERTICAL, false)
        recyclerViewExpanded.layoutManager = layoutManagerExpanded
        recyclerViewExpanded.adapter = adapterExpanded

        if (profiles.size >= 4)
            for (item in profiles.subList(4, profiles.size))
                adapterExpanded.add(BinderSection.SECTION_1, ProfileAdaptor(_context as AppCompatActivity, item))
    }
}
