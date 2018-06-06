package `in`.yeng.user.newsupdates

import `in`.yeng.user.AnimUtil
import `in`.yeng.user.MainActivity
import `in`.yeng.user.R
import `in`.yeng.user.joinwithus.helpers.ViewpagerAdapter
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class JoinWithUsFragment : Fragment {
    constructor() : super()

    companion object {
        val TAG = "FragJoinWithUs"
    }

    private var _context: Context? = null
    lateinit var viewPager: ViewPager
    lateinit var coverImage: ImageView
    lateinit var tabLayout: TabLayout

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        _context = context
    }

    override fun onDetach() {
        super.onDetach()
        _context = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.join_with_us_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coverImage = ImageView(_context)
        coverImage.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, resources.getDimensionPixelSize(R.dimen.joinwithus_imageveiw_height))
        coverImage.setImageResource(R.drawable.logo_white)
        val padding = resources.getDimensionPixelSize(R.dimen.joinwithus_imageveiw_padding)
        coverImage.setPadding(padding, padding, padding, padding)
        (_context as MainActivity).collapsingToolbarLayout.addView(coverImage)
        AnimUtil.fadeDown(coverImage, 400)

        viewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = ViewpagerAdapter((_context as AppCompatActivity).supportFragmentManager)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {

                when (position) {
                    ViewpagerAdapter.YENG -> {
                        (_context as MainActivity).collapsingToolbarLayout.removeView(coverImage)
                        coverImage.setImageResource(R.drawable.logo_white)
                        (_context as MainActivity).collapsingToolbarLayout.addView(coverImage)
                        AnimUtil.fadeUp(coverImage, 400)
                    }
                    ViewpagerAdapter.CRAZY_AMIGOS -> {
                        (_context as MainActivity).collapsingToolbarLayout.removeView(coverImage)
                        coverImage.setImageResource(R.drawable.crazy_amigos)
                        (_context as MainActivity).collapsingToolbarLayout.addView(coverImage)
                        AnimUtil.fadeDown(coverImage, 400)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

        })

        tabLayout = view.findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        (_context as MainActivity).collapsingToolbarLayout.removeView(coverImage)

    }


}