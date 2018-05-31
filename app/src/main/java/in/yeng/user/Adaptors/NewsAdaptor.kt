package `in`.yeng.user.Adaptors

import `in`.yeng.user.Activities.PdfViewer
import `in`.yeng.user.Models.Responses.NewsandUpdatesResponse
import `in`.yeng.user.R
import `in`.yeng.user.Utilities.DateHelper
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.WithHint
import android.view.View
import android.widget.TextView
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.card_news_and_update.view.*
import org.jetbrains.anko.intentFor


class NewsAdaptor(activity: AppCompatActivity, val data: NewsandUpdatesResponse) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_NEWS_UPDATE) {

    override fun layoutResId(): Int = R.layout.card_news_and_update

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view)   {
            title.text = data.tittle

            news.text = data.news
            display_date.text = "Published ".plus(DateHelper.getRelativeDate(data.displayDate))
            end_date.text = "expiry ".plus(DateHelper.getRelativeDate(data.endDate))

            data.attachmentPath?.let {
                attachment_view.visibility = View.VISIBLE
                attachment_name.text = "View Attachment"

                card.setOnClickListener {
                    context.startActivity(context.intentFor<PdfViewer>("url" to data.attachmentPath.replace("public/", "")))
                }
            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}