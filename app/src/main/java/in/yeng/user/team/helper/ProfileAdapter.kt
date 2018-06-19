package `in`.yeng.user.newsupdates.helpers

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.network.APIClient
import `in`.yeng.user.team.ProfileActivity
import `in`.yeng.user.team.dom.GroupMember
import `in`.yeng.user.team.dom.Profile
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.team_profile_card.view.*
import org.jetbrains.anko.intentFor
import java.io.Serializable


class ProfileAdapter(activity: AppCompatActivity, val data: GroupMember) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_TEAM_PROFILES) {

    override fun layoutResId(): Int = R.layout.team_profile_card

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view) {
            AnimUtil.fadeUp(this, 350, 40f, 0.98f)
            Glide.with(activity).load(APIClient.CRAZY_AMIGOS_BASEURL.plus(data.image)).into(profile_pic)
            name.text = data.name
            name.isSelected = true
            name.requestFocus()

            findViewById<View>(R.id.card).setOnClickListener {
                AnimUtil.clickAnimation(this)
                //  activity.startActivity(activity.intentFor<ProfileViewActivity>("name" to data.name, "profilePic" to data.profilePic))

                activity.startActivity(activity.intentFor<ProfileActivity>("id" to data.id))

            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}