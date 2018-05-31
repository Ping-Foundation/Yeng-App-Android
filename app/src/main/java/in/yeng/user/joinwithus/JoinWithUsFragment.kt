package `in`.yeng.user.newsupdates

import `in`.yeng.user.MainActivity
import `in`.yeng.user.R
import `in`.yeng.user.joinwithus.helpers.ViewpagerAdapter
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class JoinWithUsFragment : Fragment() {

    companion object {
        val TAG = "FragJoinWithUs"
    }

    private var _context: Context? = null
    lateinit var viewPager: ViewPager

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        _context = context
    }

    override fun onDetach() {
        super.onDetach()
        _context = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.frag_join_with_us, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = ViewpagerAdapter((_context as AppCompatActivity).supportFragmentManager)

        ( _context as MainActivity).coverImage.setImageResource(R.drawable.crazy_amigos)


    }


}