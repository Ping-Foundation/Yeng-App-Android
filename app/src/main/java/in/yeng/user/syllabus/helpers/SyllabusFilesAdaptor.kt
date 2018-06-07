package `in`.yeng.user.syllabus.helpers

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.PdfViewerActivity
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.syllabus_files_card.view.*
import org.jetbrains.anko.intentFor


open class SyllabusFilesAdaptor(val activity: AppCompatActivity, val data: String, val idStr: String) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_SYLLABUS_FILE) {

    override fun layoutResId(): Int = R.layout.syllabus_files_card

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view) {
            AnimUtil.fadeUp(this, 350, 40f, 0.98f)
            content.text = data.replace(idStr.plus("_"), "")

            findViewById<View>(R.id.card).setOnClickListener {
                AnimUtil.clickAnimation(it)
                activity?.let {
                    /*

                    https://www.yengapp.com/Syllabus/M%20Tech/S4/CSE/fgjgfhdgfh_ghjdghdj.pdf

                     */
                    val url = "/Syllabus/".plus(idStr.replace("_", "/")).plus("/${data}.pdf")

                    context.startActivity(context.intentFor<PdfViewerActivity>("url" to url))
                }

            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}