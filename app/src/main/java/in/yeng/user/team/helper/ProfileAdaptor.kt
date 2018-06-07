package `in`.yeng.user.newsupdates.helpers

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.home.MainActivity
import `in`.yeng.user.team.dom.Profile
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.team_profile_card.view.*
import org.jetbrains.anko.toast


class ProfileAdaptor(activity: AppCompatActivity, val data: Profile) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_TEAM_PROFILES) {

    override fun layoutResId(): Int = R.layout.team_profile_card

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view) {
            AnimUtil.fadeUp(this, 350, 40f, 0.98f)
            profile_pic.setImageResource(R.mipmap.ic_launcher_round)
            name.text = data.name

            findViewById<View>(R.id.card).setOnClickListener {
                AnimUtil.clickAnimation(this)
                (activity as AppCompatActivity).toast("Hello ${data.name}")
            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}