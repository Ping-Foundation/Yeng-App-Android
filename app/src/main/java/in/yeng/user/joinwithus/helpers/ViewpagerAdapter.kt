package `in`.yeng.user.joinwithus.helpers

import `in`.yeng.user.joinwithus.children.CrazyAmigosFragment
import `in`.yeng.user.joinwithus.children.YengFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewpagerAdapter(val fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> YengFragment()
                1 -> CrazyAmigosFragment()

                else -> YengFragment()
            }

    override fun getCount(): Int = 2

}