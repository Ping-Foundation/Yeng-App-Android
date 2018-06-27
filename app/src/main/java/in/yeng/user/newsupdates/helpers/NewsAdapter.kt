package `in`.yeng.user.newsupdates.helpers

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.DateHelper
import `in`.yeng.user.helpers.NetworkHelper
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.network.APIClient
import `in`.yeng.user.newsupdates.ExpandedNews
import `in`.yeng.user.newsupdates.dom.NewsRes
import android.content.Intent
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.news_and_updates_card.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import java.io.File


class NewsAdapter(activity: AppCompatActivity, val data: NewsRes) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_NEWS_UPDATE) {

    override fun layoutResId(): Int = R.layout.news_and_updates_card

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        val holder = viewHolder as ViewHolder
        with(holder.view) {
            AnimUtil.fadeIn(this, 300)
            title.text = data.tittle
            news.text = data.news
            display_date.text = "Published ".plus(DateHelper.getRelativeDate(data.displayDate))

            news_content.setOnClickListener {
                AnimUtil.clickAnimation(it)
                activity.startActivity(activity.intentFor<ExpandedNews>(
                        "title" to data.tittle,
                        "content" to data.news,
                        "attachment" to data.attachmentPath ))
            }

            data.attachmentPath?.let {

                attachment_view.visibility = View.VISIBLE
                attachment_name.text = "View Attachment"

                attachment_view.setOnClickListener {
                    progressBar.smoothToShow()
                    AnimUtil.clickAnimation(it)

                    NetworkHelper.onPDFDownloaded(activity, APIClient.YENG_BASEURL + "/" + data.attachmentPath.replace("public/", "")) {
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