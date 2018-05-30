package `in`.yeng.user.Fragments

import `in`.yeng.user.API.APIClient
import `in`.yeng.user.Activities.MainActivity
import `in`.yeng.user.Adaptors.SyllabusAdapter
import `in`.yeng.user.R
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragSyllabus : Fragment() {
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

        val layoutManager = StaggeredGridLayoutManager(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        var id = arguments?.getString("id") ?: "Syllabus"


        APIClient.getSyllabusList(id) {

            val syllabusAdapter = SyllabusAdapter(it, id, _context as AppCompatActivity)
            recyclerView.adapter = syllabusAdapter

            MainActivity.loadingIndicator.smoothToHide()
        }


    }
}