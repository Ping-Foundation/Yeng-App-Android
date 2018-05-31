package `in`.yeng.user.syllabus.helpers

import `in`.yeng.user.MainActivity
import `in`.yeng.user.R
import `in`.yeng.user.helpers.FragmentHelper
import `in`.yeng.user.syllabus.SyllabusFragment
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.card_syllabus.view.*


open class SyllabusAdaptor(val activity: AppCompatActivity, val data: String, val idStr: String) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_SYLLABUS_DIR) {

    override fun layoutResId(): Int = R.layout.card_syllabus

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view) {
            content.text = data.replace(idStr.plus("_"), "")

            findViewById<View>(R.id.card).setOnClickListener {
                activity?.let {

                    val fragSyllabus = SyllabusFragment()
                    fragSyllabus.arguments = Bundle().apply {
                        putString("id", data)
                        FragmentHelper.AddFragment(fragSyllabus, it, MainActivity.CONTAINER_LAYOUT, SyllabusFragment.TAG, 250)
                    }
                }

            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}