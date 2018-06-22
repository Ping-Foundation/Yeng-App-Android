package `in`.yeng.user.helpers

import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

object FragmentHelper {
    /*
    Delay is added for smoother operations..
     */
    var fragCount = 0

    fun replaceFragment(fragment: Fragment, activity: AppCompatActivity, layout: Int, tag: String, delayInMillis: Long) {
        Handler().postDelayed({
            activity.supportFragmentManager.beginTransaction()
                    .replace(layout, fragment, tag)
                    .commit()
            fragCount = 1
        }, delayInMillis)
    }

    fun removeFragment(fragment: Fragment, activity: AppCompatActivity, delayInMillis: Long) {
        Handler().postDelayed({
            activity.supportFragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit()
            fragCount--
        }, delayInMillis)
    }

    fun addFragment(fragment: Fragment, activity: AppCompatActivity, layout: Int, tag: String, delayInMillis: Long) {
        Handler().postDelayed({
            activity.supportFragmentManager.beginTransaction()
                    .add(layout, fragment, tag)
                    .commit()
            fragCount++
        }, delayInMillis)
    }
}