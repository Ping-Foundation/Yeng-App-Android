package `in`.yeng.user.syllabus.helpers

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.NetworkHelper
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.network.APIClient
import android.content.Intent
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.syllabus_files_card.view.*
import org.jetbrains.anko.toast
import java.io.File


open class SyllabusFilesAdapter(val activity: AppCompatActivity, val data: String, val idStr: String) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_SYLLABUS_FILE) {

    override fun layoutResId(): Int = R.layout.syllabus_files_card

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view) {
            AnimUtil.fadeUp(this, 350, 40f, 0.98f)
            content.text = data.replace(idStr.plus("_"), "")
            findViewById<View>(R.id.card).setOnClickListener {
                progressBar.smoothToShow()
                AnimUtil.clickAnimation(it)
                activity?.let {
                    /*
                    https://www.yengapp.com/Syllabus/M%20Tech/S4/CSE/fgjgfhdgfh_ghjdghdj.pdf
                     */
                    val url = "/Syllabus/".plus(idStr.replace("_", "/")).plus("/${data}.pdf")

                    //   context.startActivity(context.intentFor<PdfViewerActivity>("url" to url))

                    NetworkHelper.onPDFDownloaded(activity, APIClient.YENG_BASEURL + "/" + url) {
                        progressBar.smoothToHide()
                        val file = File(activity.cacheDir, "pdfFile.pdf")
                        val sharedURI = FileProvider.getUriForFile(activity, "in.yeng.user.PDFPROVIDER", file)

                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.setDataAndType(sharedURI, "application/pdf")
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

                        val activities = activity.packageManager.queryIntentActivities(intent, 0)
                        if (activities.isEmpty())
                            activity.toast("No PDF Viewers found. Get Adobe reader!..")
                        else
                            activity.startActivity(intent)

                    }
                }

            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}