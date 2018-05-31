package `in`.yeng.user.Fragments

import `in`.yeng.user.API.APIClient
import `in`.yeng.user.Activities.MainActivity
import `in`.yeng.user.Adaptors.BinderSection
import `in`.yeng.user.Adaptors.BinderTypes
import `in`.yeng.user.Adaptors.SyllabusAdaptor
import `in`.yeng.user.Adaptors.SyllabusFilesAdaptor
import `in`.yeng.user.R
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter

class FragSyllabus : Fragment() {

    companion object {
        val TAG = "FragSyllabus"
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
            inflater.inflate(R.layout.frag_syllabus, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainActivity.loadingIndicator.smoothToShow()

        recyclerView = view.findViewById(R.id.recycler_view)

        val layoutManager = StaggeredGridLayoutManager(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        var id = arguments?.getString("id") ?: "Syllabus"

        val adapter = RecyclerBinderAdapter<BinderSection, BinderTypes>()

        APIClient.getSyllabusList(id) { items, files ->
            _context?.let {

                recyclerView.adapter = adapter

                for (item in items)
                    adapter.add(BinderSection.SECTION_1, SyllabusAdaptor(it as AppCompatActivity, item, id))

                for (item in files)
                    adapter.add(BinderSection.SECTION_1, SyllabusFilesAdaptor(it as AppCompatActivity, item, id))

                MainActivity.loadingIndicator.smoothToHide()
            }

        }


    }

}