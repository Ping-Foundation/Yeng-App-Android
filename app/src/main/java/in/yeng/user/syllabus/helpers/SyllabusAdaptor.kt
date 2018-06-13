package `in`.yeng.user.syllabus.helpers

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.FragmentHelper
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.home.MainActivity
import `in`.yeng.user.syllabus.SyllabusFragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.syllabus_card.view.*


open class SyllabusAdaptor(val activity: AppCompatActivity, val data: String, val idStr: String) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_SYLLABUS_DIR) {

    override fun layoutResId(): Int = R.layout.syllabus_card

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view) {
            AnimUtil.fadeUp(this, 350, 40f, 0.98f)
            content.text = data.replace(idStr.plus("_"), "")

            findViewById<View>(R.id.card).setOnClickListener {
                AnimUtil.clickAnimation(it)
                val fragSyllabus = SyllabusFragment()
                fragSyllabus.arguments = Bundle().apply { putString("id", data) }
                FragmentHelper.AddFragment(fragSyllabus, activity, MainActivity.CONTAINER_LAYOUT, SyllabusFragment.TAG, 250)
            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}