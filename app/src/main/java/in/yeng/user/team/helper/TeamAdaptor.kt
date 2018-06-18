package `in`.yeng.user.newsupdates.helpers

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.viewbinders.BinderSection
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.team.dom.Team
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter
import kotlinx.android.synthetic.main.team_card.view.*


class TeamAdaptor(val activity: AppCompatActivity, val data: Team) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_TEAM_PROFILES) {

    override fun layoutResId(): Int = R.layout.team_card

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view) {
            AnimUtil.fadeIn(this, 300, 0.9f)

            team_name.text = data.name

            recycler_view.isNestedScrollingEnabled = true

            val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recycler_view.layoutManager = layoutManager

            //val dividerItemDecoration = DividerItemDecoration(recycler_view.context, LinearLayoutManager.HORIZONTAL)
            //recycler_view.addItemDecoration(dividerItemDecoration)

            val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()
            recycler_view.adapter = adapter


            for (item in data.groups)
                adapter.add(BinderSection.SECTION_1, GroupAdaptor(activity, item))
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}