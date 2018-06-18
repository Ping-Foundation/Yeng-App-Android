package `in`.yeng.user.newsupdates.helpers

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.team.TeamMembersActivity
import `in`.yeng.user.team.dom.Group
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.team_group_card.view.*
import org.jetbrains.anko.intentFor


class GroupAdaptor(activity: AppCompatActivity, val data: Group) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_TEAM_PROFILES) {

    override fun layoutResId(): Int = R.layout.team_group_card

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view) {
            AnimUtil.fadeIn(this, 300)

            group_name.text = data.name
            Glide.with(activity).load("http://192.168.100.70:3000${data.icon}").into(group_image)

            findViewById<View>(R.id.card).setOnClickListener {
                AnimUtil.clickAnimation(this)
                activity.startActivity(activity.intentFor<TeamMembersActivity>("name" to data.name, "id" to data.id))
            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}