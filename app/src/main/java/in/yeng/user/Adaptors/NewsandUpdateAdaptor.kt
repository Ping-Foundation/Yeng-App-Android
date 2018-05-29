package `in`.yeng.user.Adaptors

import `in`.yeng.user.Models.Responses.NewsandUpdatesResponse
import `in`.yeng.user.R
import `in`.yeng.user.Utilities.DateHelper
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_news_and_update.view.*
import org.jetbrains.anko.toast


class NewsandUpdateAdaptor(val data: List<NewsandUpdatesResponse>, val context: Context?) : RecyclerView.Adapter<NewsandUpdateAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_news_and_update, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: NewsandUpdatesResponse) = with(view) {
            title.text = data.tittle
            news.text = data.news
            display_date.text = "Published ".plus(DateHelper.getRelativeDate(data.displayDate))
            end_date.text = "expiry ".plus(DateHelper.getRelativeDate(data.endDate))
            data.attachmentPath?.let {
                attachment_view.visibility = View.VISIBLE
                attachment_name.text = data.attachmentName

                card.setOnClickListener {
                    context.toast("Clicked!!")
                }
            }

        }
    }

}