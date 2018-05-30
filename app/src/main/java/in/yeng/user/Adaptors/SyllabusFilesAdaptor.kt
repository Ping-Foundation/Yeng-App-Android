package `in`.yeng.user.Adaptors

import `in`.yeng.user.Activities.MainActivity
import `in`.yeng.user.Activities.PdfViewer
import `in`.yeng.user.Fragments.FragSyllabus
import `in`.yeng.user.R
import `in`.yeng.user.Utilities.FragmentHelper
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.card_syllabus.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast


open class SyllabusFilesAdaptor(val activity: AppCompatActivity, val data: String, val idStr: String) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_SYLLABUS_DIR) {

    override fun layoutResId(): Int = R.layout.card_syllabus

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view) {
            content.text = data.replace(idStr.plus("_"), "")

            findViewById<View>(R.id.card).setOnClickListener {
                activity?.let {

                    //https://www.yengapp.com/Syllabus/M%20Tech/S4/CSE/fgjgfhdgfh_ghjdghdj.pdf


                    val url = "/Syllabus/".plus(idStr.replace("_","/")).plus("/${data}.pdf")

                    context.startActivity(context.intentFor<PdfViewer>("url" to url))
                }

            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}