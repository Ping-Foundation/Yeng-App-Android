package `in`.yeng.user.newsupdates

import `in`.yeng.user.R
import `in`.yeng.user.helpers.viewbinders.BinderSection
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.home.MainActivity
import `in`.yeng.user.newsupdates.helpers.NewsAdaptor
import `in`.yeng.user.newsupdates.network.NewsAPI
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter


class NewsUpdateFragment : Fragment() {

    companion object {
        val TAG = "FragNewsAndUpdates"
    }

    private var _context: Context? = null
    lateinit var recyclerView: RecyclerView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        _context = context
    }

    override fun onDetach() {
        super.onDetach()
        _context = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.news_and_updates_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainActivity.loadingIndicator.smoothToShow()

        recyclerView = view.findViewById(R.id.recycler_view)

        val layoutManager = LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager


        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()
        recyclerView.adapter = adapter

        NewsAPI.getNews { items ->
            _context?.let {
                for (item in items.asReversed())
                    adapter.add(BinderSection.SECTION_1, NewsAdaptor(it as AppCompatActivity, item))
                MainActivity.loadingIndicator.smoothToHide()
            }

        }


    }


}