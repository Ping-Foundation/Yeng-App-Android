package `in`.yeng.user.newsupdates.helpers

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.DateHelper
import `in`.yeng.user.helpers.PdfViewerActivity
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.newsupdates.dom.NewsRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.news_and_updates_card.view.*
import org.jetbrains.anko.intentFor


class NewsAdaptor(activity: AppCompatActivity, val data: NewsRes) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_NEWS_UPDATE) {

    override fun layoutResId(): Int = R.layout.news_and_updates_card

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        val holder = viewHolder as ViewHolder
            with(holder.view) {
                AnimUtil.fadeIn(this,300)
                title.text = data.tittle
                news.text = data.news
                display_date.text = "Published ".plus(DateHelper.getRelativeDate(data.displayDate))
                end_date.text = "expiry ".plus(DateHelper.getRelativeDate(data.endDate))
                data.attachmentPath?.let {

                    attachment_view.visibility = View.VISIBLE
                    attachment_name.text = "View Attachment"

                    card.setOnClickListener {
                        AnimUtil.clickAnimation(it)
                        context.startActivity(context.intentFor<PdfViewerActivity>("url" to data.attachmentPath.replace("public/", "")))
                    }
                }
            }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}