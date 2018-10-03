package `in`.yeng.user.newsupdates

import `in`.yeng.user.R
import `in`.yeng.user.helpers.DateHelper
import `in`.yeng.user.helpers.viewbinders.BinderSection
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.home.MainActivity
import `in`.yeng.user.newsupdates.helpers.NewsAdapter
import `in`.yeng.user.newsupdates.network.NewsAPI
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter


class NewsUpdateFragment : Fragment() {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    private var attached = false
    lateinit var recyclerView: RecyclerView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        attached = true
    }

    override fun onDetach() {
        super.onDetach()
        attached = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.news_and_updates_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)

        (context as MainActivity).retry.setOnClickListener {
            callNewsAPI()
            if (context != null)
                (context as MainActivity).setupDrawer()
        }

        callNewsAPI()

    }

    private fun callNewsAPI() {
        MainActivity.loadingIndicator.smoothToShow()

        NewsAPI.getNews { items, status ->

            MainActivity.loadingIndicator.smoothToHide()

            when (status) {
                200 -> {
                    if (attached) {
                        if (items != null) {
                            (context!! as MainActivity).noConnection.visibility = View.GONE

                            val layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
                            recyclerView.layoutManager = layoutManager

                            val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()
                            recyclerView.adapter = adapter

                            if (items.isEmpty())
                                (context!! as MainActivity).noContent.visibility = View.VISIBLE
                            else {
                                (context!! as MainActivity).noContent.visibility = View.GONE
                                for (item in items.asReversed()) {
                                    if (DateHelper.getTimeStamp(item.endDate) >= System.currentTimeMillis()) {
                                        adapter.add(BinderSection.SECTION_1, NewsAdapter(context!! as AppCompatActivity, item))
                                    }

                                }
                            }
                        } else
                            (context!! as MainActivity).noContent.visibility = View.VISIBLE
                    }
                }

                404 -> {
                    if (attached) {
                        (context!! as MainActivity).noConnection.visibility = View.VISIBLE
                        (context!! as MainActivity).noContent.visibility = View.GONE
                    }

                }
            }
        }
    }
}