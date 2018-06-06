package `in`.yeng.user.joinwithus.helpers

import `in`.yeng.user.joinwithus.children.CrazyAmigosFragment
import `in`.yeng.user.joinwithus.children.YengFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewpagerAdapter(val fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    companion object {
        val YENG = 0
        val CRAZY_AMIGOS = 1
    }

    override fun getItem(position: Int): Fragment =
            when (position) {
                YENG -> YengFragment()
                CRAZY_AMIGOS -> CrazyAmigosFragment()

                else -> YengFragment()
            }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position)   {
            YENG -> "Yeng"
            CRAZY_AMIGOS -> "Crazy Amigos"

            else -> "Yeng"
        }
    }

}