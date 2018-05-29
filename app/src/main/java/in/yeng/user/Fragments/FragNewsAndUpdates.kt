package `in`.yeng.user.Fragments

import `in`.yeng.user.API.APIClient
import `in`.yeng.user.Activities.MainActivity
import `in`.yeng.user.Adaptors.NewsandUpdateAdapter
import `in`.yeng.user.R
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_news_and_updates.*

class FragNewsAndUpdates : Fragment() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.frag_news_and_updates, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false)

        recyclerView = view.findViewById(R.id.recycler_view)

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.layoutManager = layoutManager

        MainActivity.loadingIndicator.smoothToShow()


        APIClient.getNews {
            recyclerView.adapter = NewsandUpdateAdapter(it, _context)
            MainActivity.loadingIndicator.smoothToHide()
        }

    }



}